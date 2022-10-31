package top.getawaycar.rbac.common.pojo.vo;

import lombok.*;

import java.util.List;

/**
 * UI界面Cascader（级联选择器）下的Option
 *
 * @author EricJeppesen
 * @date 2022/10/14 17:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UiCascaderOptionVO {

    /**
     * 标签（用于展示）
     */
    private String label;

    /**
     * 值（用于提交）
     */
    private String value;

    /**
     * 含有子节点
     */
    private Boolean leaf;

    public Boolean getLeaf() {
        if (this.leaf != null) {
            return leaf;
        }
        return true;
    }
}
