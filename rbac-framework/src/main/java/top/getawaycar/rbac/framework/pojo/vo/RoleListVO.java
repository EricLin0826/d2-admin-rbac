package top.getawaycar.rbac.framework.pojo.vo;

import lombok.*;

import java.util.List;

/**
 * 角色列表视图
 *
 * @author EricJeppesen
 * @date 2022/10/21 23:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RoleListVO {

    /**
     * 标识
     */
    private String id;
    /**
     * 唯一标识
     */
    private String role;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 状态
     */
    private Integer status;

}
