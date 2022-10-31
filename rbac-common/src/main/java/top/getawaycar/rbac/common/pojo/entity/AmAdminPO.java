package top.getawaycar.rbac.common.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;


/**
 * 用户表
 * 
 * @author EricJeppesen
 * @date 2022-10-14 02:51 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("am_admin")
public class AmAdminPO {

    /**
     * 编号
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 联系电话
     */
    private String contactTelephone;
    /**
     * 电子邮箱
     */
    private String emailAddress;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐值
     */
    private String salt;
    /**
     * 是否锁定
     */
    private Integer locked;
    /**
     * 数据状态（0锁定，1正常）
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer dataStatus;
    /**
     * 创建时间（时间戳）
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;
    /**
     * 最后一次修改时间（时间戳）
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long lastUpdateTime;

}
