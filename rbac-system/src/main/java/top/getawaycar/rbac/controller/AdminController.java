package top.getawaycar.rbac.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.getawaycar.rbac.framework.pojo.dto.create.CreateAdminDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyAdminDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyAdminPasswordDTO;
import top.getawaycar.rbac.framework.pojo.dto.query.AdminPageQueryDTO;
import top.getawaycar.rbac.framework.pojo.vo.AdminDetailVO;
import top.getawaycar.rbac.framework.pojo.vo.AdminListVO;
import top.getawaycar.rbac.framework.service.IAdminService;

/**
 * 后台用户Api控制器
 *
 * @author EricJeppesen
 * @date 2022/11/3 18:14
 */
@RestController
public class AdminController {

    private IAdminService adminService;

    @Autowired
    public void setAdminService(IAdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("admin")
    public Long createAdmin(@RequestBody CreateAdminDTO adminDTO) {
        return adminService.createAdmin(adminDTO);
    }

    @PutMapping("admin/{id}")
    public Integer modifyAdmin(@PathVariable("id") Long id, @RequestBody ModifyAdminDTO modifyAdminDTO) {
        return adminService.modifyAdmin(id, modifyAdminDTO);
    }

    @GetMapping("admin/{id}")
    public AdminDetailVO getAdmin(@PathVariable("id") Long id) {
        return adminService.getAdmin(id);
    }

    @PostMapping("admins")
    public Page<AdminListVO> modifyAdmin(@RequestBody AdminPageQueryDTO pageQueryDTO) {
        return adminService.listAdminByCondition(pageQueryDTO);
    }

    @PutMapping("admin/{id}/status/{status}")
    public Integer modifyAdminLockStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        return adminService.modifyAdminLockStatus(id, status);
    }

    @PutMapping("admin/{id}/password")
    public Integer modifyAdminPassword(@PathVariable("id") Long id, @RequestBody ModifyAdminPasswordDTO modifyAdminPasswordDTO) {
        return adminService.modifyAdminPassword(id, modifyAdminPasswordDTO);
    }

}
