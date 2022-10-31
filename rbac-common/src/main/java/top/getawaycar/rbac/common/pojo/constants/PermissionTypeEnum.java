package top.getawaycar.rbac.common.pojo.constants;

/**
 * @author EricJeppesen
 * @date 2022/10/14 16:51
 */
public enum PermissionTypeEnum {

    /**
     * 菜单
     */
    MENU(1, "菜单"),
    /**
     * 按钮
     */
    BUTTON(2, "按钮");

    private Integer value;

    private String description;

    PermissionTypeEnum(Integer value, String description) {
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
