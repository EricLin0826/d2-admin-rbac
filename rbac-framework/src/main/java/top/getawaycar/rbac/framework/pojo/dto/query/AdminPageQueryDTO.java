package top.getawaycar.rbac.framework.pojo.dto.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.getawaycar.rbac.common.pojo.dto.BasePageQueryDTO;

/**
 * 后台用户分页查询DTO
 *
 * @author EricJeppesen
 * @date 2022/11/3 20:42
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class AdminPageQueryDTO extends BasePageQueryDTO {

    /**
     * 名称或用户名
     */
    private String name;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 电子邮箱
     */
    private String email;

}
