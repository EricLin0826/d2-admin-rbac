package top.getawaycar.rbac.framework.pojo.vo;

import lombok.*;

import java.util.List;

/**
 * 资源表
 *
 * @author EricJeppesen
 * @date 2022-10-14 02:51 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PermissionTreeVO {
    /**
     * 标识
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 资源类型(1.菜单 2.按钮或文本块)
     */
    private Integer type;
    /**
     * 父编号
     */
    private String parentId;
    /**
     * 父编号列表
     */
    private String parentIds;
    /**
     * 权限字符串
     */
    private String permission;
    /**
     * 路由路径
     */
    private String path;
    /**
     * 组件本地路径
     */
    private String componentPath;
    /**
     * 组件名称
     */
    private String componentName;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 是否有效
     */
    private Integer status;
    /**
     * 权限配置
     */
    private String config;
    /**
     * 数据状态（0锁定，1正常）
     */
    private Integer dataStatus;
    /**
     * 子节点
     */
    private List<PermissionTreeVO> children;
    /**
     * 是否含有子节点
     */
    private Boolean hasChildren;

    public Boolean getHasChildren() {
        if (this.hasChildren != null) {
            return hasChildren;
        }
        return true;
    }
}
