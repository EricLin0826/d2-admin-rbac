package top.getawaycar.rbac.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;


/**
 * 资源表
 *
 * @author EricJeppesen
 * @date 2022-10-14 03:05 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("am_permission")
public class AmPermissionPO {

    /**
     * 编号
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 名称
     */
    @TableField("`name`")
    private String name;
    /**
     * 资源类型(1.菜单 2.按钮或文本块)
     */
    @TableField("`type`")
    private Integer type;
    /**
     * 父编号
     */
    @TableField("`parent_id`")
    private Long parentId;
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
    @TableField("`path`")
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
    @TableField("`icon`")
    private String icon;
    /**
     * 排序
     */
    @TableField("`sort`")
    private Integer sort;
    /**
     * 是否有效
     */
    @TableField("`status`")
    private Integer status;
    /**
     * 权限配置
     */
    @TableField("`config`")
    private String config;
    /**
     * 数据状态（0锁定，1正常）
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer dataStatus;
    /**
     * 重定向地址
     */
    private String redirectUrl;
}
