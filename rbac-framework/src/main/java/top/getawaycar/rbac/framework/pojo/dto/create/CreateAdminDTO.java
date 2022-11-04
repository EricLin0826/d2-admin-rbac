package top.getawaycar.rbac.framework.pojo.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 创建后台用户数据传输类
 *
 * @author EricJeppesen
 * @date 2022/11/3 18:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateAdminDTO {
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
    private List<Long> roleIds;
}
