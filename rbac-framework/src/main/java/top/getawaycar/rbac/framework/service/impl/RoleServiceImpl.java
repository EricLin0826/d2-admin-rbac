package top.getawaycar.rbac.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.getawaycar.rbac.common.dao.RoleDAO;
import top.getawaycar.rbac.common.dao.RolePermissionDAO;
import top.getawaycar.rbac.common.factory.QueryWrapperFactory;
import top.getawaycar.rbac.common.pojo.entity.AmRolePO;
import top.getawaycar.rbac.common.pojo.entity.AmRolePermissionPO;
import top.getawaycar.rbac.framework.mapping.RoleBeanMapping;
import top.getawaycar.rbac.framework.pojo.dto.create.CreateRoleDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyRoleDTO;
import top.getawaycar.rbac.framework.pojo.vo.RoleDetailVO;
import top.getawaycar.rbac.framework.service.IRoleService;

import java.util.List;

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
        Long roleId = roleEntity.getId();
        int insert = roleDAO.insert(roleEntity);
        if (insert > 0) {
            // 批量插入数据
            List<Long> permissionIds = createRoleDTO.getPermissionIds();
            rolePermissionDAO.saveRelatePermissionBatch(roleId, permissionIds, System.currentTimeMillis());
        }
        return roleId;
    }

    @Override
    public RoleDetailVO getRoleDetailById(Long id) {
        AmRolePO roleEntity = roleDAO.selectOne(QueryWrapperFactory.build(id));
        if (roleEntity != null) {
            List<AmRolePermissionPO> relatePermissions = rolePermissionDAO.selectList(QueryWrapperFactory.build("role_id", id));
        }
        return null;
    }

    @Override
    public Integer modifyRoleById(Long id, ModifyRoleDTO modifyRoleDTO) {
        return null;
    }
}
