package top.getawaycar.rbac.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.getawaycar.rbac.common.pojo.entity.AmAdminRolePO;

import java.util.List;

/**
 * 后台用户角色对应-MybatisPlus数据库操作
 *
 * @author EricJeppesen
 * @date 2022/11/2 17:17
 */
@Component
public interface AdminRoleDAO extends BaseMapper<AmAdminRolePO> {

    /**
     * 保存关联的权限标识
     *
     * @param adminId           后台用户标识
     * @param roleIds           角色标识
     * @param currentTimestamps 时间戳
     * @return 批量插入数量
     */
    Integer saveRelateRoleBatch(@Param("adminId") Long adminId, @Param("roleIds") List<Long> roleIds, @Param("currentTimestamps") Long currentTimestamps);


}
