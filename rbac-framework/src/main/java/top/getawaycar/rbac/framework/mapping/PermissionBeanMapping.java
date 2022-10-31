package top.getawaycar.rbac.framework.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import top.getawaycar.rbac.common.pojo.entity.AmPermissionPO;
import top.getawaycar.rbac.common.pojo.vo.UiCascaderOptionVO;
import top.getawaycar.rbac.framework.pojo.dto.create.CreatePermissionDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyPermissionDTO;
import top.getawaycar.rbac.framework.pojo.vo.PermissionTreeVO;
import top.getawaycar.rbac.framework.pojo.vo.PermissionVO;
import top.getawaycar.rbac.framework.pojo.vo.RouterVO;

import java.util.List;

/**
 * BeanToBean映射
 *
 * @author EricJeppesen
 * @date 2022/10/14 15:07
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissionBeanMapping {

    /**
     * 创建DTO转数据库映射类
     *
     * @param saveDTO 创建DTO
     * @return 数据库映射类
     */
    AmPermissionPO fromCreateDtoToPo(CreatePermissionDTO saveDTO);

    /**
     * 修改DTO转数据库映射类
     *
     * @param modifyDTO 修改DTO
     * @return 数据库映射类
     */
    AmPermissionPO fromModifyDtoToPo(ModifyPermissionDTO modifyDTO);

    /**
     * 数据库映射类转化为Vue的Router视图类
     *
     * @param permission 数据库映射类
     * @return
     */
    RouterVO fromPoToRouterVo(AmPermissionPO permission);

    /**
     * 数据库映射类转化为Vue的Router视图类
     *
     * @param permissions 数据库映射类
     * @return Vue的Router视图类
     */
    @Mappings(value = {@Mapping(target = "component", source = "componentPath"), @Mapping(target = "meta", source = "config")})
    List<RouterVO> fromPoToRouterVo(List<AmPermissionPO> permissions);

    /**
     * 数据库实体转化为权限详情视图类
     *
     * @param permission 数据库实体
     * @return 权限详情视图类
     */
    PermissionVO fromPoToPermissionVo(AmPermissionPO permission);

    /**
     * 数据库实体类转化为级联选择器Ui界面
     *
     * @param permission 数据库实体
     * @return 级联选择器Ui界面
     */
    @Mappings(value = {@Mapping(target = "label", source = "name"), @Mapping(target = "value", source = "id")})
    UiCascaderOptionVO fromPoToUiCascaderOptionVo(AmPermissionPO permission);

    /**
     * 数据库实体类转化为级联选择器Ui界面
     *
     * @param permissions 数据库实体
     * @return 级联选择器Ui界面
     */
    List<UiCascaderOptionVO> fromPoToUiCascaderOptionVo(List<AmPermissionPO> permissions);

    /**
     * 数据库映射类转树形视图类
     *
     * @param permission 数据库映射类
     * @return 树形视图类
     */
    PermissionTreeVO fromPoToTreeVo(AmPermissionPO permission);

    /**
     * 数据库映射类转树形视图类
     *
     * @param permissions 数据库映射类
     * @return 树形视图类
     */
    List<PermissionTreeVO> fromPoToTreeVo(List<AmPermissionPO> permissions);


}
