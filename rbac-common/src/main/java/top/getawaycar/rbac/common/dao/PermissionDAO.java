package top.getawaycar.rbac.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import top.getawaycar.rbac.common.pojo.entity.AmPermissionPO;

/**
 * 权限-MybatisPlus数据库操作
 *
 * @author EricJeppesen
 * @date 2022/10/14 15:08
 */
@Repository
public interface PermissionDAO extends BaseMapper<AmPermissionPO> {
}
