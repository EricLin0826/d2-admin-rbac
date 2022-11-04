package top.getawaycar.rbac.framework.pojo.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 登录数据传输类
 *
 * @author EricJeppesen
 * @date 2022/11/3 22:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 验证码标识
     */
    private String verifyCodeId;

}
