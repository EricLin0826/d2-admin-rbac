package top.getawaycar.rbac.framework.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 后台用户列表视图
 *
 * @author EricJeppesen
 * @date 2022/11/3 20:40
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class AdminListVO {

    /**
     * 标识
     */
    private String id;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 联系电话
     */
    private String contactTelephone;
    /**
     * 电子邮箱
     */
    private String emailAddress;
    /**
     * 用户名
     */
    private String username;
    /**
     * 是否锁定
     */
    private Integer locked;
}
