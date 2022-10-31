package top.getawaycar.rbac.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;


/**
 * 角色表
 * 
 * @author EricJeppesen
 * @date 2022-10-14 02:51 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("am_role")
public class AmRolePO {

    /**
     * 编号
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
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
     * 状态 1.正常 0.禁用
     */
    private Integer status;
    /**
     * 数据状态（0锁定，1正常）
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer dataStatus;

}
