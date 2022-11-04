package top.getawaycar.rbac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.getawaycar.rbac.framework.pojo.dto.other.LoginDTO;
import top.getawaycar.rbac.framework.pojo.vo.CaptchaVO;
import top.getawaycar.rbac.framework.pojo.vo.LoginVO;
import top.getawaycar.rbac.framework.pojo.vo.PermissionNavVO;
import top.getawaycar.rbac.framework.service.ICommonService;
import top.getawaycar.rbac.framework.util.SecurityUtil;

import java.util.List;

/**
 * 通用API控制器
 *
 * @author EricJeppesen
 * @date 2022/11/2 14:49
 */
@RestController
public class CommonController {

    private ICommonService commonService;

    @Autowired
    public void setCommonService(ICommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping("captcha")
    public CaptchaVO getCaptcha() {
        return commonService.getCaptcha();
    }

    @PostMapping("login")
    public LoginVO login(@RequestBody LoginDTO loginDTO) {
        return commonService.login(loginDTO.getUsername(), loginDTO.getPassword(), loginDTO.getVerifyCode(), loginDTO.getVerifyCodeId());
    }

    @GetMapping("nav")
    public List<PermissionNavVO> nav() {
        String username = SecurityUtil.getUsername();
        return commonService.getPermissionNavByUsername(username);
    }

}
