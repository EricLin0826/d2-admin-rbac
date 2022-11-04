package top.getawaycar.rbac.framework.pojo.vo;

import lombok.*;

import java.util.List;

/**
 * 角色详情视图
 *
 * @author EricJeppesen
 * @date 2022/10/21 23:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RoleDetailVO {

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
     * 权限标识
     */
    private List<String> permissionIds;

}
