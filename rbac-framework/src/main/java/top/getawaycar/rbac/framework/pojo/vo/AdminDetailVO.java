package top.getawaycar.rbac.framework.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 后台用户详情视图
 *
 * @author EricJeppesen
 * @date 2022/11/3 18:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDetailVO {
    /**
     * 标识
     */
    private Long id;
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
     * 角色标识
     */
    private List<String> roleIds;
}
