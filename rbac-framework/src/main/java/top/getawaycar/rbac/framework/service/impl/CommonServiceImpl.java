package top.getawaycar.rbac.framework.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.exceptions.StatefulException;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.xml.internal.ws.api.model.MEP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import top.getawaycar.rbac.common.dao.AdminDAO;
import top.getawaycar.rbac.common.dao.AdminRoleDAO;
import top.getawaycar.rbac.common.dao.PermissionDAO;
import top.getawaycar.rbac.common.dao.RolePermissionDAO;
import top.getawaycar.rbac.common.factory.QueryWrapperFactory;
import top.getawaycar.rbac.common.pojo.entity.AmAdminPO;
import top.getawaycar.rbac.common.pojo.entity.AmAdminRolePO;
import top.getawaycar.rbac.common.pojo.entity.AmPermissionPO;
import top.getawaycar.rbac.common.pojo.entity.AmRolePermissionPO;
import top.getawaycar.rbac.common.util.GetawayCarRedisUtil;
import top.getawaycar.rbac.framework.constant.Constants;
import top.getawaycar.rbac.framework.pojo.dto.other.AdminAuthorizationDTO;
import top.getawaycar.rbac.framework.pojo.vo.CaptchaVO;
import top.getawaycar.rbac.framework.pojo.vo.LoginVO;
import top.getawaycar.rbac.framework.pojo.vo.PermissionNavVO;
import top.getawaycar.rbac.framework.service.ICommonService;
import top.getawaycar.rbac.framework.util.TokenUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 通用服务
 *
 * @author EricJeppesen
 * @date 2022/11/2 11:58
 */
@Service
public class CommonServiceImpl implements ICommonService {

    private GetawayCarRedisUtil redisUtil;

    @Autowired
    public void setRedisUtil(GetawayCarRedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Value("${redis.cache.captcha.prefix:}")
    private String CAPTCHA_CACHE_PREFIX;

    @Value("${redis.cache.captcha.remaining:30}")
    private Integer CAPTCHA_CACHE_REMAINING;

    @Override
    public CaptchaVO getCaptcha() {
        GifCaptcha gifCaptcha = CaptchaUtil.createGifCaptcha(180, 76, 4);
        //获取到4位数的验证码
        String code = gifCaptcha.getCode();
        Snowflake snowflake = IdUtil.getSnowflake();
        String codeId = snowflake.nextIdStr();
        // 过期时间单位：分钟
        redisUtil.set(CAPTCHA_CACHE_PREFIX + codeId, code, CAPTCHA_CACHE_REMAINING * 60);
        return new CaptchaVO(codeId, gifCaptcha.getImageBase64());
    }

    @Override
    public boolean verifyCaptcha(String verifyCodeId, String verifyCode) {
        String cacheKey = CAPTCHA_CACHE_PREFIX + verifyCodeId;
        Object objectCode = redisUtil.get(cacheKey);
        redisUtil.del(cacheKey);
        if (objectCode == null) {
            throw new StatefulException(4003, "验证码已过期");
        }
        String code = (String) objectCode;
        return Objects.equals(code, verifyCode);
    }

    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private TokenUtil tokenUtil;

    @Autowired
    public void setTokenUtil(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Override
    public LoginVO login(String username, String password, String code, String uuid) {
        if (!this.verifyCaptcha(uuid, code)) {
            throw new StatefulException(4003, "验证码错误");
        }

        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new StatefulException(5000, "密码错误");
            } else {
                throw new StatefulException(5000, e);
            }
        }
        AdminAuthorizationDTO loginAdmin = (AdminAuthorizationDTO) authentication.getPrincipal();
        String contactTelephone = loginAdmin.getContactTelephone();
        String realName = loginAdmin.getRealName();
        String id = String.valueOf(loginAdmin.getId());
        String token = tokenUtil.createToken(loginAdmin);
        // 生成token
        return new LoginVO(id, contactTelephone, realName, token);
    }

    private AdminRoleDAO adminRoleDAO;

    @Autowired
    public void setAdminRoleDAO(AdminRoleDAO adminRoleDAO) {
        this.adminRoleDAO = adminRoleDAO;
    }

    private AdminDAO adminDAO;

    @Autowired
    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    private RolePermissionDAO rolePermissionDAO;

    @Autowired
    public void setRolePermissionDAO(RolePermissionDAO rolePermissionDAO) {
        this.rolePermissionDAO = rolePermissionDAO;
    }

    private PermissionDAO permissionDAO;

    @Autowired
    public void setPermissionDAO(PermissionDAO permissionDAO) {
        this.permissionDAO = permissionDAO;
    }

    @Override
    public List<PermissionNavVO> getPermissionNavByUsername(String username) {
        AmAdminPO adminEntity = adminDAO.selectOne(QueryWrapperFactory.build("username", username));
        List<AmAdminRolePO> relateRoles = adminRoleDAO.selectList(QueryWrapperFactory.build("admin_id", adminEntity.getId()));
        List<Long> relateRoleIds = relateRoles.stream().map(AmAdminRolePO::getRoleId).collect(Collectors.toList());
        QueryWrapper<AmRolePermissionPO> rolePermissionQueryWrapper = QueryWrapperFactory.build();
        rolePermissionQueryWrapper.in("role_id", relateRoleIds);
        List<AmRolePermissionPO> rolePermissionEntities = rolePermissionDAO.selectList(rolePermissionQueryWrapper);
        List<Long> permissionIds = rolePermissionEntities.stream().map(AmRolePermissionPO::getPermissionId).distinct().collect(Collectors.toList());
        QueryWrapper<AmPermissionPO> permissionQueryWrapper = QueryWrapperFactory.build();
        permissionQueryWrapper.in("`id`", permissionIds);
        permissionQueryWrapper.eq("`type`", 1);
        List<AmPermissionPO> permissionEntities = permissionDAO.selectList(permissionQueryWrapper);
        ArrayList<PermissionNavVO> permissionNavs = new ArrayList<>();
        permissionEntities.forEach(item -> {
            PermissionNavVO permissionNavItem = new PermissionNavVO();
            permissionNavItem.setComponent(item.getComponentPath());
            permissionNavItem.setId(String.valueOf(item.getId()));
            permissionNavItem.setParentId(String.valueOf(item.getParentId()));
            permissionNavItem.setPath(item.getPath());
            permissionNavItem.setName(item.getComponentName());
            permissionNavItem.setRedirect(item.getRedirectUrl());
            PermissionNavVO.MetaInformationVO metaInformationVO = new PermissionNavVO.MetaInformationVO();
            metaInformationVO.setIcon(item.getIcon());
            metaInformationVO.setTitle(item.getName());
            metaInformationVO.setShow(Boolean.TRUE);
            permissionNavItem.setMeta(metaInformationVO);
            permissionNavs.add(permissionNavItem);
        });
        return permissionNavs;
    }
}
