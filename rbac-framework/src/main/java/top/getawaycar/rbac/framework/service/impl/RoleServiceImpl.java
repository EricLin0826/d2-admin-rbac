package top.getawaycar.rbac.framework.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.getawaycar.rbac.common.dao.PermissionDAO;
import top.getawaycar.rbac.common.dao.RoleDAO;
import top.getawaycar.rbac.common.dao.RolePermissionDAO;
import top.getawaycar.rbac.common.factory.QueryWrapperFactory;
import top.getawaycar.rbac.common.pojo.constants.DBDataStatusEnum;
import top.getawaycar.rbac.common.pojo.entity.AmPermissionPO;
import top.getawaycar.rbac.common.pojo.entity.AmRolePO;
import top.getawaycar.rbac.common.pojo.entity.AmRolePermissionPO;
import top.getawaycar.rbac.common.pojo.vo.UiSelectOptionVO;
import top.getawaycar.rbac.framework.mapping.RoleBeanMapping;
import top.getawaycar.rbac.framework.pojo.dto.create.CreateRoleDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyRoleDTO;
import top.getawaycar.rbac.framework.pojo.dto.query.KeywordPageQueryDTO;
import top.getawaycar.rbac.framework.pojo.vo.RoleDetailVO;
import top.getawaycar.rbac.framework.pojo.vo.RoleListVO;
import top.getawaycar.rbac.framework.service.IRoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 角色接口实现类
 *
 * @author EricJeppesen
 * @date 2022/10/22 10:14
 */
@Service
public class RoleServiceImpl implements IRoleService {

    private RolePermissionDAO rolePermissionDAO;

    private RoleDAO roleDAO;

    private RoleBeanMapping roleBeanMapping;

    @Autowired
    public void setRolePermissionDAO(RolePermissionDAO rolePermissionDAO) {
        this.rolePermissionDAO = rolePermissionDAO;
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Autowired
    public void setRoleBeanMapping(RoleBeanMapping roleBeanMapping) {
        this.roleBeanMapping = roleBeanMapping;
    }


    @Override
    public Long createRole(CreateRoleDTO createRoleDTO) {
        AmRolePO roleEntity = roleBeanMapping.fromCreateDtoToPo(createRoleDTO);
        int insertEffectiveRole = roleDAO.insert(roleEntity);
        Long roleId = roleEntity.getId();
        if (insertEffectiveRole > 0) {
            // 批量插入数据
            List<Long> permissionIds = createRoleDTO.getPermissionIds();
            List<Long> currentPermissionIds = new ArrayList<>();

            permissionIds.forEach(item -> {
                this.resolvePermission(item, currentPermissionIds);
            });
            // 去重 并且 排除掉根节点
            List<Long> finalPermissionIds = currentPermissionIds.stream().distinct().filter(data -> {
                if (data == 0) {
                    return false;
                }
                return true;
            }).collect(Collectors.toList());

            // 批量保存
            rolePermissionDAO.saveRelatePermissionBatch(roleId, finalPermissionIds, System.currentTimeMillis());
        }
        return roleId;
    }

    @Override
    public Integer modifyRoleStatus(Long id, Integer status) {
        QueryWrapper<AmRolePO> updateWrapper = QueryWrapperFactory.build(id);
        AmRolePO roleEntity = new AmRolePO();
        roleEntity.setStatus(status);
        return roleDAO.update(roleEntity, updateWrapper);
    }

    private PermissionDAO permissionDAO;

    @Autowired
    public void setPermissionDAO(PermissionDAO permissionDAO) {
        this.permissionDAO = permissionDAO;
    }

    public void resolvePermission(Long parentPermissionId, List<Long> permissionIds) {
        permissionIds.add(parentPermissionId);
        List<AmPermissionPO> permissions = permissionDAO.selectList(QueryWrapperFactory.build("parent_id", parentPermissionId));
        if (permissions == null || permissions.size() < 0) {
            return;
        }
        List<Long> currentPermissionIds = permissions.stream().map(AmPermissionPO::getId).collect(Collectors.toList());
        permissionIds.addAll(currentPermissionIds);
        for (Long currentPermissionId : currentPermissionIds) {
            this.resolvePermission(currentPermissionId, permissionIds);
        }
    }

    @Override
    public RoleDetailVO getRoleDetailById(Long id) {
        AmRolePO roleEntity = roleDAO.selectOne(QueryWrapperFactory.build(id));
        List<Long> permissionIds = null;
        if (roleEntity != null) {
            List<AmRolePermissionPO> relatePermissions = rolePermissionDAO.selectList(QueryWrapperFactory.build("role_id", id));
            permissionIds = relatePermissions.stream().map(AmRolePermissionPO::getPermissionId).collect(Collectors.toList());
        }
        return roleBeanMapping.fromPoToVo(roleEntity, permissionIds);
    }

    @Override
    public Integer modifyRoleById(Long id, ModifyRoleDTO modifyRoleDTO) {
        AmRolePO roleEntity = roleBeanMapping.fromModifyDtoToPo(modifyRoleDTO);
        int updateEffectiveRow = roleDAO.update(roleEntity, QueryWrapperFactory.build(id));

        // 删除之前角色对应的权限
        rolePermissionDAO.delete(QueryWrapperFactory.build("role_id", id));

        // 批量插入数据
        List<Long> permissionIds = modifyRoleDTO.getPermissionIds();
        List<Long> currentPermissionIds = new ArrayList<>();

        permissionIds.forEach(item -> {
            this.resolvePermission(item, currentPermissionIds);
        });
        // 去重 并且 排除掉根节点
        List<Long> finalPermissionIds = currentPermissionIds.stream().distinct().filter(data -> {
            if (data == 0) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());

        // 将新数据插入进去
        rolePermissionDAO.saveRelatePermissionBatch(id, finalPermissionIds, System.currentTimeMillis());

        return updateEffectiveRow;
    }

    @Override
    public Page<RoleListVO> listRolesByKeyword(KeywordPageQueryDTO pageQueryDTO) {
        String keyword = pageQueryDTO.getKeryword();
        QueryWrapper<AmRolePO> queryWrapper = QueryWrapperFactory.build();
        if (StrUtil.isNotEmpty(keyword)) {
            queryWrapper.like("`name`", keyword)
                    .or().like("`role`", keyword)
                    .or().like("`description`", keyword);
        }
        Page<AmRolePO> rolePage = new Page<>(pageQueryDTO.getPageNumber(), pageQueryDTO.getPageSize());
        return roleBeanMapping.fromPoToVo(roleDAO.selectPage(rolePage, queryWrapper));
    }

    @Override
    public List<UiSelectOptionVO> listEnableRoles() {
        QueryWrapper<AmRolePO> queryWrapper = QueryWrapperFactory.build();
        queryWrapper.eq("`status`", 1);
        List<AmRolePO> roleEntities = roleDAO.selectList(queryWrapper);
        return roleBeanMapping.fromPoToSelectVo(roleEntities);
    }
}
