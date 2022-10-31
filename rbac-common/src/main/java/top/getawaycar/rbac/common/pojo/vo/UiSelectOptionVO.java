package top.getawaycar.rbac.common.pojo.vo;

import lombok.*;

/**
 * UI界面Select下的Option
 *
 * @author EricJeppesen
 * @date 2022/10/14 17:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UiSelectOptionVO {

    /**
     * 标签（用于展示）
     */
    private String label;

    /**
     * 值（用于提交）
     */
    private String value;

}
