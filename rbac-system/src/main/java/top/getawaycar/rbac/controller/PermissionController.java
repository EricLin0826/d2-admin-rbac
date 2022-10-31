package top.getawaycar.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.getawaycar.rbac.common.pojo.vo.UiCascaderOptionVO;
import top.getawaycar.rbac.framework.pojo.dto.create.CreatePermissionDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyPermissionDTO;
import top.getawaycar.rbac.framework.pojo.vo.PermissionTreeVO;
import top.getawaycar.rbac.framework.pojo.vo.PermissionVO;
import top.getawaycar.rbac.framework.service.IPermissionService;

import java.util.List;

/**
 * 权限控制器
 *
 * @author EricJeppesen
 * @date 2022/10/14 19:53
 */
@RestController
public class PermissionController {

    private IPermissionService permissionService;

    @Autowired
    public void setPermissionService(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 创建权限
     *
     * @param createPermissionDTO 创建权限所需要的数据
     * @return 创建后的标识
     */
    @PostMapping("permission")
    public Long createPermission(@RequestBody CreatePermissionDTO createPermissionDTO) {
        return permissionService.createPermission(createPermissionDTO);
    }

    /**
     * 获取权限列表
     *
     * @param parentId 父级标识(0为根目录)
     * @return Tree类型数据
     */
    @GetMapping("permission/tree/table/{parentId}")
    public List<PermissionTreeVO> listPermissionTreeTable(@PathVariable Long parentId) {
        return permissionService.listPermissionTree(parentId);
    }

    /**
     * 获取Cascader选项
     *
     * @param parentId 父级标识(0为根目录)
     * @return 级联数据
     */
    @GetMapping("permission/cascader/options/{parentId}")
    public List<UiCascaderOptionVO> listPermissionCascaderOptions(@PathVariable Long parentId) {
        return permissionService.listPermissionsAsCascaderByParentId(parentId);
    }

    /**
     * 根据标识获取权限
     *
     * @param id 标识
     * @return 结果
     */
    @GetMapping("permission/{id}")
    public PermissionVO getPermission(@PathVariable Long id) {
        return permissionService.getPermissionById(id);
    }

    /**
     * 修改权限内容
     *
     * @param id                  权限标识
     * @param modifyPermissionDTO 修改内容
     * @return 数据库受影响行数
     */
    @PutMapping("permission/{id}")
    public Integer modifyPermission(@PathVariable Long id, @RequestBody ModifyPermissionDTO modifyPermissionDTO) {
        return permissionService.modifyPermissionById(id, modifyPermissionDTO);
    }

}
