package top.getawaycar.rbac.framework.pojo.dto.modify;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 修改后台用户密码数据传输类
 *
 * @author EricJeppesen
 * @date 2022/11/3 21:08
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ModifyAdminPasswordDTO {

    /**
     * 密码
     */
    private String password;

    /**
     * 再次确认密码
     */
    private String confirmPassword;

}
