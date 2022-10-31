package top.getawaycar.rbac.framework.service;

import top.getawaycar.rbac.common.pojo.vo.UiCascaderOptionVO;
import top.getawaycar.rbac.framework.pojo.dto.create.CreatePermissionDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyPermissionDTO;
import top.getawaycar.rbac.framework.pojo.vo.MenuVO;
import top.getawaycar.rbac.framework.pojo.vo.PermissionTreeVO;
import top.getawaycar.rbac.framework.pojo.vo.PermissionVO;
import top.getawaycar.rbac.framework.pojo.vo.RouterVO;

import java.util.List;

/**
 * 权限服务
 *
 * @author EricJeppesen
 * @date 2022/10/14 14:58
 */
public interface IPermissionService {

    /**
     * 创建权限
     *
     * @param createPermissionDTO 创建权限所需数据
     * @return 创建完成后的标识
     */
    Long createPermission(CreatePermissionDTO createPermissionDTO);

    /**
     * 根据角色查询出权限，并且转换为菜单
     *
     * @param roleIds 角色标识
     * @return 菜单
     */
    List<MenuVO> listMenuByRoleIds(List<Long> roleIds);

    /**
     * 根据角色查询出权限，并且转换为路由
     *
     * @param roleIds 路由标识
     * @return 路由
     */
    List<RouterVO> listRouterByRoleIds(List<Long> roleIds);

    /**
     * 根据标识查询权限
     *
     * @param id 标识
     * @return 权限全映射, 主要用于修改权限
     */
    PermissionVO getPermissionById(Long id);

    /**
     * 根据标识修改权限
     *
     * @param id                  权限标识
     * @param modifyPermissionDTO 修改内容
     * @return 数据库受影响行数
     */
    Integer modifyPermissionById(Long id, ModifyPermissionDTO modifyPermissionDTO);

    /**
     * 根据父级标识查询权限，并且转化为级联选择器中的选项
     *
     * @param parentId 父级标识(0为根节点)
     * @return 级联选择器中的选项
     */
    List<UiCascaderOptionVO> listPermissionsAsCascaderByParentId(Long parentId);

    /**
     * 根据父级标识查询权限，并且转化为Table下的选择器
     *
     * @param parentId 父级标识(0为根节点)
     * @return Table的树节点
     */
    List<PermissionTreeVO> listPermissionTree(Long parentId);


}
