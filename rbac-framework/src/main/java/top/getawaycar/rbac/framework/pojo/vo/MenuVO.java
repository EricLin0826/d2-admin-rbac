package top.getawaycar.rbac.framework.pojo.vo;

import lombok.*;

import java.util.List;

/**
 * @author EricJeppesen
 * @date 2022/10/14 15:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MenuVO {

    /**
     * 路由路径
     */
    private String path;

    /**
     * 名称title
     */
    private String name;

    /**
     * ICON
     */
    private String icon;

    /**
     * 子节点
     */
    private List<MenuVO> children;

}
