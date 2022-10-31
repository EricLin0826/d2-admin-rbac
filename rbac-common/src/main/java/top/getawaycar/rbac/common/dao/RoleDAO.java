package top.getawaycar.rbac.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import top.getawaycar.rbac.common.pojo.entity.AmRolePO;

import java.util.List;

/**
 * 角色数据库操作接口
 *
 * @author EricJeppesen
 * @date 2022/10/22 10:15
 */
@Repository
public interface RoleDAO extends BaseMapper<AmRolePO> {


}
