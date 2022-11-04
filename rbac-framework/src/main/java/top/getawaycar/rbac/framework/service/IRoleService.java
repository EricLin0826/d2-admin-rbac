package top.getawaycar.rbac.framework.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.getawaycar.rbac.common.pojo.entity.AmRolePO;
import top.getawaycar.rbac.common.pojo.vo.UiSelectOptionVO;
import top.getawaycar.rbac.framework.pojo.dto.create.CreateRoleDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyRoleDTO;
import top.getawaycar.rbac.framework.pojo.dto.query.KeywordPageQueryDTO;
import top.getawaycar.rbac.framework.pojo.vo.RoleDetailVO;
import top.getawaycar.rbac.framework.pojo.vo.RoleListVO;

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
     * 修改角色状态
     *
     * @param id     角色标识
     * @param status 状态
     * @return 数据库受影响行数
     */
    Integer modifyRoleStatus(Long id, Integer status);

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

    /**
     * 根据关键词查询角色
     *
     * @param pageQueryDTO 关键字
     * @return 查询出来的分页数据
     */
    Page<RoleListVO> listRolesByKeyword(KeywordPageQueryDTO pageQueryDTO);

    /**
     * 查询出可使用的角色
     *
     * @return {@link UiSelectOptionVO} 查询出角色数据选择器视图
     */
    List<UiSelectOptionVO> listEnableRoles();

}
