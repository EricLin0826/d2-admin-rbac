package top.getawaycar.rbac.framework.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.getawaycar.rbac.common.dao.AdminDAO;
import top.getawaycar.rbac.common.dao.AdminRoleDAO;
import top.getawaycar.rbac.common.dao.RoleDAO;
import top.getawaycar.rbac.common.factory.QueryWrapperFactory;
import top.getawaycar.rbac.common.pojo.entity.AmAdminPO;
import top.getawaycar.rbac.common.pojo.entity.AmAdminRolePO;
import top.getawaycar.rbac.common.pojo.entity.AmRolePO;
import top.getawaycar.rbac.common.pojo.exception.AdminUsernameExistException;
import top.getawaycar.rbac.framework.mapping.AdminBeanMapping;
import top.getawaycar.rbac.framework.pojo.dto.create.CreateAdminDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyAdminDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyAdminPasswordDTO;
import top.getawaycar.rbac.framework.pojo.dto.other.AdminAuthorizationDTO;
import top.getawaycar.rbac.framework.pojo.dto.query.AdminPageQueryDTO;
import top.getawaycar.rbac.framework.pojo.vo.AdminDetailVO;
import top.getawaycar.rbac.framework.pojo.vo.AdminListVO;
import top.getawaycar.rbac.framework.service.IAdminService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户服务接口实现类
 *
 * @author EricJeppesen
 * @date 2022/11/2 17:15
 */
@Service
public class AdminServiceImpl implements IAdminService {

    private AdminDAO adminDAO;

    private AdminRoleDAO adminRoleDAO;

    @Autowired
    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Autowired
    public void setAdminRoleDAO(AdminRoleDAO adminRoleDAO) {
        this.adminRoleDAO = adminRoleDAO;
    }

    private RoleDAO roleDAO;

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    private AdminBeanMapping adminBeanMapping;

    @Autowired
    public void setAdminBeanMapping(AdminBeanMapping adminBeanMapping) {
        this.adminBeanMapping = adminBeanMapping;
    }

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询用户
        AmAdminPO adminEntity = adminDAO.selectOne(QueryWrapperFactory.build("username", username));
        if (adminEntity == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 查询关联角色
        List<AmAdminRolePO> relateRoleEntities = adminRoleDAO.selectList(QueryWrapperFactory.build("admin_id", adminEntity.getId()));

        // 取出关联角色Id
        List<Long> roleIds = relateRoleEntities.stream().map(AmAdminRolePO::getRoleId).collect(Collectors.toList());

        // 查询所有角色结果
        QueryWrapper<AmRolePO> roleQueryWrapper = QueryWrapperFactory.build();
        roleQueryWrapper.in("id", roleIds);
        List<AmRolePO> roleEntities = roleDAO.selectList(roleQueryWrapper);

        return adminBeanMapping.fromPoToDto(adminEntity, roleEntities);
    }

    @Value("${getawaycar.admin.default.password:123456}")
    private String defaultPassword;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createAdmin(CreateAdminDTO createAdminDTO) {
        String username = createAdminDTO.getUsername();
        Long usernameCount = adminDAO.selectCount(QueryWrapperFactory.build("username", username));
        if (usernameCount > 0) {
            throw new AdminUsernameExistException();
        }

        AmAdminPO adminEntity = adminBeanMapping.fromCreateDtoToPo(createAdminDTO);
        String password = passwordEncoder.encode(defaultPassword);
        adminEntity.setPassword(password);
        // 默认开启
        adminEntity.setLocked(0);
        int insertEffectiveRow = adminDAO.insert(adminEntity);
        Long id = adminEntity.getId();
        if (insertEffectiveRow > 0) {
            adminRoleDAO.saveRelateRoleBatch(id, createAdminDTO.getRoleIds(), System.currentTimeMillis());
        }
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer modifyAdmin(Long id, ModifyAdminDTO modifyAdminDTO) {
        AmAdminPO adminEntity = adminBeanMapping.fromModifyDtoToPo(modifyAdminDTO);
        int updateEffectiveRow = adminDAO.update(adminEntity, QueryWrapperFactory.build(id));
        adminRoleDAO.delete(QueryWrapperFactory.build("admin_id", id));
        if (updateEffectiveRow > 0) {
            adminRoleDAO.saveRelateRoleBatch(id, modifyAdminDTO.getRoleIds(), System.currentTimeMillis());
        }
        return updateEffectiveRow;
    }

    @Override
    public Page<AdminListVO> listAdminByCondition(AdminPageQueryDTO pageQueryDTO) {
        Page<AmAdminPO> adminPage = new Page<>(pageQueryDTO.getPageNumber(), pageQueryDTO.getPageSize());
        QueryWrapper<AmAdminPO> queryWrapper = QueryWrapperFactory.build();
        String telephone = pageQueryDTO.getTelephone();
        if (StrUtil.isNotEmpty(telephone)) {
            queryWrapper.like("contact_telephone", pageQueryDTO.getTelephone());
        }
        String email = pageQueryDTO.getEmail();
        if (StrUtil.isNotEmpty(email)) {
            queryWrapper.like("email_address", email);
        }
        String name = pageQueryDTO.getName();
        if (StrUtil.isNotEmpty(name)) {
            queryWrapper.like("real_name", name).or().like("username", name);
        }
        Page<AmAdminPO> adminEntityPage = adminDAO.selectPage(adminPage, queryWrapper);
        return adminBeanMapping.fromPoToListVo(adminEntityPage);
    }

    @Override
    public AdminDetailVO getAdmin(Long id) {
        AmAdminPO adminEntity = adminDAO.selectOne(QueryWrapperFactory.build(id));
        if (adminEntity != null) {
            AdminDetailVO adminDetailVO = adminBeanMapping.fromPoToDetailVo(adminEntity);
            List<AmAdminRolePO> relateRoleEntities = adminRoleDAO.selectList(QueryWrapperFactory.build("admin_id", adminEntity.getId()));
            List<String> relateRoleIds = relateRoleEntities.stream().map(AmAdminRolePO::getRoleId).map(x -> {
                return String.valueOf(x);
            }).collect(Collectors.toList());
            adminDetailVO.setRoleIds(relateRoleIds);
            return adminDetailVO;
        }
        return null;
    }

    @Override
    public Integer modifyAdminLockStatus(Long id, Integer targetLockStatus) {
        AmAdminPO adminEntity = new AmAdminPO();
        adminEntity.setLocked(targetLockStatus);
        return adminDAO.update(adminEntity, QueryWrapperFactory.build(id));
    }

    @Override
    public Integer modifyAdminPassword(Long id, ModifyAdminPasswordDTO modifyAdminPasswordDTO) {
        String password = modifyAdminPasswordDTO.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        AmAdminPO adminEntity = new AmAdminPO();
        adminEntity.setPassword(encodedPassword);
        return adminDAO.update(adminEntity, QueryWrapperFactory.build(id));
    }
}
