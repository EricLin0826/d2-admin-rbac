package top.getawaycar.rbac.framework.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 导航视图
 *
 * @author EricJeppesen
 * @date 2022/11/4 0:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PermissionNavVO {

    //{"meta": {"icon": "user", "show": true, "title": "个人设置", "hideHeader": true, "hideChildren": true}, "name": "settings", "redirect": "/account/settings/base", "component": "AccountSettings"}

    //{"meta": {"icon": "suitcase", "show": true, "title": "后台用户权限"}, "name": "admin-permission-index", "path": "/admin/permission/index", "component": "PermissionList"}

    /**
     * 标识
     */
    private String id;
    /**
     * 父级标识
     */
    private String parentId;
    /**
     * 源数据
     */
    private MetaInformationVO meta;

    /**
     * 名称
     */
    private String name;

    /**
     * URL Path
     */
    private String path;

    /**
     * 组件位置
     */
    private String component;

    /**
     * 重定向
     */
    private String redirect;

    /**
     * Meta数据
     */
    @ToString
    @Data
    @AllArgsConstructor
    public static class MetaInformationVO {
        private String icon;

        private Boolean show;

        private String title;

        public MetaInformationVO() {
            this.show = true;
        }
    }

}
