package top.getawaycar.rbac.framework.pojo.vo;

import cn.hutool.core.util.StrUtil;
import lombok.*;

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
public class PermissionVO {
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
     * 重定向地址
     */
    private String redirectUrl;
    public String[] getParentIds() {
        return parentIds.split(StrUtil.SLASH);
    }
}
