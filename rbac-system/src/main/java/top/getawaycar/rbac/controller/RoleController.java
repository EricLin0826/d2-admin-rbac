package top.getawaycar.rbac.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.getawaycar.rbac.common.pojo.vo.UiSelectOptionVO;
import top.getawaycar.rbac.framework.pojo.dto.create.CreateRoleDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyRoleDTO;
import top.getawaycar.rbac.framework.pojo.dto.query.KeywordPageQueryDTO;
import top.getawaycar.rbac.framework.pojo.vo.RoleDetailVO;
import top.getawaycar.rbac.framework.pojo.vo.RoleListVO;
import top.getawaycar.rbac.framework.service.IRoleService;

import java.util.List;

/**
 * 角色控制器
 *
 * @author EricJeppesen
 * @date 2022/10/31 14:13
 */
@RestController
public class RoleController {

    private IRoleService roleService;

    @Autowired
    public void setRoleService(IRoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 创建角色
     *
     * @param createRoleDTO 角色内容
     * @return 新建角色的标识
     */
    @PostMapping("role")
    public Long createRole(@RequestBody CreateRoleDTO createRoleDTO) {
        return roleService.createRole(createRoleDTO);
    }

    /**
     * 修改角色状态
     *
     * @param roleId 角色标识
     * @param status 状态
     * @return 数据库受影响行数
     */
    @PutMapping("role/{roleId}/status/{status}")
    public Integer modifyRoleStatus(@PathVariable Long roleId, @PathVariable Integer status) {
        return roleService.modifyRoleStatus(roleId, status);
    }

    /**
     * 修改角色信息
     *
     * @param id            标识
     * @param modifyRoleDTO 修改的数据
     * @return 数据库受影响行数
     */
    @PutMapping("role/{id}")
    public Integer modifyRole(@PathVariable("id") Long id, @RequestBody ModifyRoleDTO modifyRoleDTO) {
        return roleService.modifyRoleById(id, modifyRoleDTO);
    }

    /**
     * 根据标识查询
     *
     * @param id 标识
     * @return 数据详情（包含角色信息）
     */
    @GetMapping("role/{id}")
    public RoleDetailVO getRole(@PathVariable("id") Long id) {
        return roleService.getRoleDetailById(id);
    }

    /**
     * 根据关键字获取用户数据
     *
     * @param keywordPageQueryDTO 只带关键字的分页
     * @return {@link RoleListVO} 列表视图
     */
    @PostMapping("roles")
    public Page<RoleListVO> listRoles(@RequestBody KeywordPageQueryDTO keywordPageQueryDTO) {
        return roleService.listRolesByKeyword(keywordPageQueryDTO);
    }

    /**
     * 查询出角色选择器视图
     *
     * @return {@link UiSelectOptionVO} 角色选择器视图
     */
    @GetMapping("roles/select")
    public List<UiSelectOptionVO> listSelectRoles() {
        return roleService.listEnableRoles();
    }
}
