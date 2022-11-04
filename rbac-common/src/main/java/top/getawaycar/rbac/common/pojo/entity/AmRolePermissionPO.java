package top.getawaycar.rbac.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * null
 *
 * @author EricJeppesen
 * @date 2022-10-14 02:51 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("am_role_permission")
public class AmRolePermissionPO {

    /**
     * 标识
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 角色标识
     */
    private Long roleId;
    /**
     * 权限标识
     */
    private Long permissionId;
    /**
     * 数据状态
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer dataStatus;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
    /**
     * 最后一次更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long lastUpdateTime;

    public AmRolePermissionPO(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }
}
