package top.getawaycar.rbac.framework.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 登录后返回视图
 *
 * @author EricJeppesen
 * @date 2022/11/3 22:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginVO {

    /**
     * 用户标识
     */
    private String id;
    /**
     * 联系电话
     */
    private String contactTelephone;
    /**
     * 真实姓名
     */
    private String realName;

    /**
     * Token
     */
    private String token;
}
