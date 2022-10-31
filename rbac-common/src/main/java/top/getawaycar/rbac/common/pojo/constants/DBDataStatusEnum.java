package top.getawaycar.rbac.common.pojo.constants;

/**
 * 数据状态枚举类型
 *
 * @author EricJeppesen
 * @date 2022/10/14 15:21
 */
public enum DBDataStatusEnum {

    /**
     * 数据正常状态
     */
    ENABLE(1, "数据正常状态"),
    /**
     * 数据删除状态
     */
    DISABLE(0, "数据删除状态");
    /**
     * 值
     */
    private final Integer value;
    /**
     * 描述
     */
    private final String description;

    DBDataStatusEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
