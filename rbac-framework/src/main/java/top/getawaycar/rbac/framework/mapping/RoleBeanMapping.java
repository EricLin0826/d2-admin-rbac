package top.getawaycar.rbac.framework.mapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import top.getawaycar.rbac.common.pojo.entity.AmRolePO;
import top.getawaycar.rbac.common.pojo.entity.AmRolePermissionPO;
import top.getawaycar.rbac.common.pojo.vo.UiSelectOptionVO;
import top.getawaycar.rbac.framework.pojo.dto.create.CreateRoleDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyRoleDTO;
import top.getawaycar.rbac.framework.pojo.vo.RoleDetailVO;
import top.getawaycar.rbac.framework.pojo.vo.RoleListVO;

import java.util.List;

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

    /**
     * 从视图类 转 工具类
     *
     * @param roleEntity    数据库实体信息
     * @param permissionIds 关联上的权限
     * @return 页面视图类
     */
    RoleDetailVO fromPoToVo(AmRolePO roleEntity, List<Long> permissionIds);

    /**
     * 从修改数据传输类 转 数据库映射类
     *
     * @param modifyRoleDTO 修改数据
     * @return 数据库映射类
     */
    AmRolePO fromModifyDtoToPo(ModifyRoleDTO modifyRoleDTO);

    /**
     * 将数据库对象转化为视图对象
     *
     * @param rolePage 数据库角色对象
     * @return 视图对象
     */
    Page<RoleListVO> fromPoToVo(Page<AmRolePO> rolePage);

    /**
     * 从数据库实体映射为选择器视图实体
     *
     * @param roleEntities 数据库实体
     * @return {@link UiSelectOptionVO} 选择器视图实体
     */
    List<UiSelectOptionVO> fromPoToSelectVo(List<AmRolePO> roleEntities);

    /**
     * 从数据库实体映射为选择器视图实体
     *
     * @param roleEntity 数据库实体
     * @return {@link UiSelectOptionVO} 选择器视图实体
     */
    @Mappings(value = {@Mapping(source = "id", target = "value"), @Mapping(source = "name", target = "label")})
    UiSelectOptionVO fromPoToSelectVo(AmRolePO roleEntity);
}
