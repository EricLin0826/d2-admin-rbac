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
public class RouterVO {

    /**
     * 路由路径
     */
    private String path;

    /**
     * 名称title
     */
    private String name;

    /**
     * 本地组件地址
     */
    private String component;

    /**
     * Meta数据
     */
    private String meta;


}
