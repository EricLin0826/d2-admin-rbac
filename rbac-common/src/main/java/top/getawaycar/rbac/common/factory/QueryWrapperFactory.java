package top.getawaycar.rbac.common.factory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.getawaycar.rbac.common.pojo.constants.DBDataStatusEnum;

/**
 * 查询条件构建工厂
 *
 * @author EricJeppesen
 * @date 2022/10/14 15:18
 */
public class QueryWrapperFactory {

    /**
     * 数据状态列名称
     */
    private static final String DATA_STATUS_FILED = "data_status";
    /**
     * 标识列名称
     */
    private static final String ID_FILED = "id";

    /**
     * 构建普通查询条件
     *
     * @param <T> 泛型
     * @return 查询条件 WHERE data_status = 1
     */
    public static <T> QueryWrapper<T> build() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DATA_STATUS_FILED, DBDataStatusEnum.ENABLE.getValue());
        return queryWrapper;
    }

    /**
     * 构建查询条件并且带上标识
     *
     * @param <T> 泛型
     * @param id  标识
     * @return 查询条件 WHERE data_status = 1 AND id = ${id}
     */
    public static <T> QueryWrapper<T> build(Object id) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DATA_STATUS_FILED, DBDataStatusEnum.ENABLE.getValue());
        queryWrapper.eq(ID_FILED, id);
        return queryWrapper;
    }

    /**
     * 构建查询条件并且带上标识
     * 在查询第三方中间表时建议使用该方法
     *
     * @param <T>         泛型
     * @param idFiledName 标识列名称
     * @param id          标识
     * @return 查询条件 WHERE data_status = 1 AND ${idFiledName} = ${id}
     */
    public static <T> QueryWrapper<T> build(String idFiledName, Object id) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DATA_STATUS_FILED, DBDataStatusEnum.ENABLE.getValue());
        queryWrapper.eq(idFiledName, id);
        return queryWrapper;
    }

    /**
     * 构建删除的查询条件
     *
     * @param <T> 泛型
     * @return 查询条件 WHERE data_status = 0
     */
    public static <T> QueryWrapper<T> buildRemovedQueryWrapper() {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DATA_STATUS_FILED, DBDataStatusEnum.DISABLE.getValue());
        return queryWrapper;
    }


}
