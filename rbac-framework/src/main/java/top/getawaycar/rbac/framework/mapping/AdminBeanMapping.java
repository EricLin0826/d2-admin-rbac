package top.getawaycar.rbac.framework.mapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import top.getawaycar.rbac.common.dao.AdminRoleDAO;
import top.getawaycar.rbac.common.pojo.entity.AmAdminPO;
import top.getawaycar.rbac.common.pojo.entity.AmAdminRolePO;
import top.getawaycar.rbac.common.pojo.entity.AmRolePO;
import top.getawaycar.rbac.framework.pojo.dto.create.CreateAdminDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyAdminDTO;
import top.getawaycar.rbac.framework.pojo.dto.other.AdminAuthorizationDTO;
import top.getawaycar.rbac.framework.pojo.vo.AdminDetailVO;
import top.getawaycar.rbac.framework.pojo.vo.AdminListVO;

import java.util.List;

/**
 * 后台用户实体映射
 *
 * @author EricJeppesen
 * @date 2022/11/2 17:21
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdminBeanMapping {

    /**
     * 将后台用户信息、后台用户对应角色 赋值成一个SpringSecurity授权西悉尼
     *
     * @param adminEntity  后台用户信息
     * @param roleEntities 后台用户对应的角色
     * @return {@link AdminAuthorizationDTO} 信息
     */
    @Mappings(value = {@Mapping(source = "roleEntities", target = "roles")})
    AdminAuthorizationDTO fromPoToDto(AmAdminPO adminEntity, List<AmRolePO> roleEntities);

    /**
     * 从创建类转化为数据库映射类
     *
     * @param createAdminDTO 创建类
     * @return {@link AmAdminPO} 数据库映射类
     */
    AmAdminPO fromCreateDtoToPo(CreateAdminDTO createAdminDTO);

    /**
     * 从数据修改类转化为数据库映射类
     *
     * @param modifyAdminDTO 修改数据传输类
     * @return {@link AmAdminPO} 数据库映射类
     */
    AmAdminPO fromModifyDtoToPo(ModifyAdminDTO modifyAdminDTO);

    /**
     * 从数据库映射类转化为列表视图类
     *
     * @param adminEntityPage 数据库映射类
     * @return {@link AdminListVO} 列表视图类
     */
    Page<AdminListVO> fromPoToListVo(Page<AmAdminPO> adminEntityPage);

    /**
     * 从数据库映射类转详情视图类
     *
     * @param adminEntity 数据库映射类
     * @return {@link AdminDetailVO} 详情视图类
     */
    AdminDetailVO fromPoToDetailVo(AmAdminPO adminEntity);

}
