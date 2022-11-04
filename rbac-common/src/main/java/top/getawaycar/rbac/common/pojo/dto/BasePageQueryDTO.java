package top.getawaycar.rbac.common.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 分页数据基础类
 *
 * @author EricJeppesen
 * @date 2022/11/3 10:37
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BasePageQueryDTO {

    private Long pageNumber;
    private Long pageSize;
    private Integer order;

    public void setPageNumber(Long pageNumber) {
        if (pageNumber == null || pageNumber <= 0) {
            this.pageNumber = 1L;
        }
        this.pageNumber = pageNumber;
    }

    public void setPageSize(Long pageSize) {
        if (pageSize == null || pageSize <= 0) {
            this.pageSize = 10L;
        }
        this.pageSize = pageSize;
    }

    public Long getPageNumber() {
        if (pageNumber == null || pageNumber <= 0) {
            return 1L;
        }
        return pageNumber;
    }

    public Long getPageSize() {
        if (pageSize == null || pageSize <= 0) {
            return 20L;
        }
        return pageSize;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getOrder() {
        return order;
    }
}
