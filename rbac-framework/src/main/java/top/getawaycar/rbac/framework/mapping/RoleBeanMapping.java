package top.getawaycar.rbac.framework.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import top.getawaycar.rbac.common.pojo.entity.AmRolePO;
import top.getawaycar.rbac.framework.pojo.dto.create.CreateRoleDTO;

/**
 * 角色Bean映射接口
 *
 * @author EricJeppesen
 * @date 2022/10/22 10:18
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleBeanMapping {

    /**
     * 从创建数据传输类 转 数据库映射类
     *
     * @param createDTO 创建数据传输类
     * @return 数据库映射类
     */
    AmRolePO fromCreateDtoToPo(CreateRoleDTO createDTO);

}
