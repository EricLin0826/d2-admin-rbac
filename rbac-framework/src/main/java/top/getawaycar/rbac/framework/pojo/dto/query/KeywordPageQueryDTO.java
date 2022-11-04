package top.getawaycar.rbac.framework.pojo.dto.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import top.getawaycar.rbac.common.pojo.dto.BasePageQueryDTO;

/**
 * 根据关键字查询分页数据
 *
 * @author EricJeppesen
 * @date 2022/11/3 10:37
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class KeywordPageQueryDTO extends BasePageQueryDTO {

    /**
     * 关键字
     */
    private String keryword;

}
