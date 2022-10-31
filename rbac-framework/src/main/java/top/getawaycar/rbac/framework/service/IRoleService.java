package top.getawaycar.rbac.framework.service;

import top.getawaycar.rbac.framework.pojo.dto.create.CreateRoleDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyRoleDTO;
import top.getawaycar.rbac.framework.pojo.vo.RoleDetailVO;

import java.util.List;

/**
 * 角色管理接口
 *
 * @author EricJeppesen
 * @date 2022/10/14 18:02
 */
public interface IRoleService {

    /**
     * 创建角色
     *
     * @param createRoleDTO 创建数据
     * @return 角色标识
     */
    Long createRole(CreateRoleDTO createRoleDTO);

    /**
     * 根据标识获取角色详情
     *
     * @param id 角色标识
     * @return 角色详情
     */
    RoleDetailVO getRoleDetailById(Long id);

    /**
     * 根据标识修改角色数据
     *
     * @param id            标识
     * @param modifyRoleDTO 要修改的数据
     * @return 数据库受影响行数
     */
    Integer modifyRoleById(Long id, ModifyRoleDTO modifyRoleDTO);

}
