package top.getawaycar.rbac.framework.configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import top.getawaycar.rbac.common.pojo.constants.DBDataStatusEnum;

/**
 * MyBatisPlus-字段注解-拦截器
 *
 * @author EricJeppesen
 */
@Slf4j
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 起始版本 3.3.0(推荐使用)
        Integer value = DBDataStatusEnum.ENABLE.getValue();
        Long currentTime = System.currentTimeMillis();
        this.strictInsertFill(metaObject, "createTime", Long.class, currentTime);
        this.strictInsertFill(metaObject, "dataStatus", Integer.class, value);
    }


    @Override
    public void updateFill(MetaObject metaObject) {
        // 起始版本 3.3.0(推荐使用)
        this.strictUpdateFill(metaObject, "lastUpdateTime", Long.class, System.currentTimeMillis());
    }
}
