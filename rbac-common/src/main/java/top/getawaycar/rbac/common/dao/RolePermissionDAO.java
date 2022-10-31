package top.getawaycar.rbac.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.getawaycar.rbac.common.pojo.entity.AmRolePermissionPO;

import java.util.List;

/**
 * 角色关联权限-MybatisPlus数据库操作
 *
 * @author EricJeppesen
 * @date 2022/10/14 15:08
 */
@Repository
public interface RolePermissionDAO extends BaseMapper<AmRolePermissionPO> {

    /**
     * 保存关联的权限标识
     *
     * @param roleId            角色标识
     * @param permissionIds     权限标识集合
     * @param currentTimestamps 时间戳
     * @return 批量插入数量
     */
    Integer saveRelatePermissionBatch(@Param("roleId") Long roleId, @Param("permissionIds") List<Long> permissionIds, @Param("currentTimestamps") Long currentTimestamps);

}
