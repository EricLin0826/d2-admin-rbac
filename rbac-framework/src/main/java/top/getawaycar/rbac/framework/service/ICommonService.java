package top.getawaycar.rbac.framework.service;

import top.getawaycar.rbac.framework.pojo.vo.CaptchaVO;
import top.getawaycar.rbac.framework.pojo.vo.LoginVO;
import top.getawaycar.rbac.framework.pojo.vo.PermissionNavVO;

import java.util.List;

/**
 * 通用服务接口
 *
 * @author EricJeppesen
 * @date 2022/11/2 11:53
 */
public interface ICommonService {

    /**
     * 获取验证码
     *
     * @return {@link CaptchaVO} 验证码
     */
    CaptchaVO getCaptcha();

    /**
     * 校验验证码
     *
     * @param verifyCodeId 验证码标识
     * @param verifyCode   验证码
     * @return 填写的验证码是否正确
     */
    boolean verifyCaptcha(String verifyCodeId, String verifyCode);

    /**
     * 登录
     *
     * @param username     用户名
     * @param password     密码
     * @param code         验证码
     * @param verifyCodeId 验证码标识
     * @return {@link LoginVO} 登陆后数据 姓名、标识、电话
     */
    LoginVO login(String username, String password, String code, String verifyCodeId);

    /**
     * 根据后台用户名获取导航数据
     *
     * @param username 后台用户名
     * @return 导航视图数据
     */
    List<PermissionNavVO> getPermissionNavByUsername(String username);

}
