package top.getawaycar.rbac.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import top.getawaycar.rbac.common.pojo.entity.AmAdminPO;

/**
 * 后台用户-MybatisPlus数据库操作
 *
 * @author EricJeppesen
 * @date 2022/11/2 17:17
 */
@Component
public interface AdminDAO extends BaseMapper<AmAdminPO> {
}
