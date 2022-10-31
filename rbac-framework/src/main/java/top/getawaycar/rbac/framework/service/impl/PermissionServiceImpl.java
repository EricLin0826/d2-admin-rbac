package top.getawaycar.rbac.framework.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.getawaycar.rbac.common.dao.PermissionDAO;
import top.getawaycar.rbac.common.dao.RolePermissionDAO;
import top.getawaycar.rbac.common.factory.QueryWrapperFactory;
import top.getawaycar.rbac.common.pojo.entity.AmPermissionPO;
import top.getawaycar.rbac.common.pojo.entity.AmRolePermissionPO;
import top.getawaycar.rbac.common.pojo.vo.UiCascaderOptionVO;
import top.getawaycar.rbac.framework.mapping.PermissionBeanMapping;
import top.getawaycar.rbac.framework.pojo.dto.create.CreatePermissionDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyPermissionDTO;
import top.getawaycar.rbac.framework.pojo.vo.MenuVO;
import top.getawaycar.rbac.framework.pojo.vo.PermissionTreeVO;
import top.getawaycar.rbac.framework.pojo.vo.PermissionVO;
import top.getawaycar.rbac.framework.pojo.vo.RouterVO;
import top.getawaycar.rbac.framework.service.IPermissionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 权限服务接口实现类
 *
 * @author EricJeppesen
 * @date 2022/10/14 15:07
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    private PermissionDAO permissionDAO;

    @Autowired
    public void setPermissionDAO(PermissionDAO permissionDAO) {
        this.permissionDAO = permissionDAO;
    }

    private PermissionBeanMapping permissionBeanMapping;

    @Autowired
    public void setPermissionBeanMapping(PermissionBeanMapping permissionBeanMapping) {
        this.permissionBeanMapping = permissionBeanMapping;
    }

    /**
     * 根节点标识
     */
    private static final String ROOT_STRING_ID = "0/";
    private static final Long ROOT_LONG_ID = 0L;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createPermission(CreatePermissionDTO createPermissionDTO) {
        // 数据转换
        AmPermissionPO entity = permissionBeanMapping.fromCreateDtoToPo(createPermissionDTO);
        // 判断是否有根节点
        Long parentId = entity.getParentId();
        if (parentId == null) {
            entity.setParentId(ROOT_LONG_ID);
            parentId = entity.getParentId();
        }

        // 填充父节点
        if (parentId != 0) {
            String parentNodesParentIds = this.getParentNodesParentIds(parentId);
            entity.setParentIds(parentNodesParentIds);
        } else {
            entity.setParentIds(ROOT_STRING_ID);
        }

        // 插入数据库中
        int insert = permissionDAO.insert(entity);
        if (insert > 0) {
            return entity.getId();
        }
        return null;
    }

    private String getParentNodesParentIds(Long parentNodeId) {
        // 构建查询条件
        QueryWrapper<AmPermissionPO> condition = QueryWrapperFactory.build(parentNodeId);
        // 查询结构
        AmPermissionPO result = permissionDAO.selectOne(condition);
        if (result != null) {
            return result.getParentIds() + parentNodeId + StrUtil.SLASH;
        }
        return StrUtil.EMPTY;
    }

    private RolePermissionDAO rolePermissionDAO;

    @Autowired
    public void setRolePermissionDAO(RolePermissionDAO rolePermissionDAO) {
        this.rolePermissionDAO = rolePermissionDAO;
    }

    @Override
    public List<MenuVO> listMenuByRoleIds(List<Long> roleIds) {
        QueryWrapper<AmRolePermissionPO> rolePermissionQueryCondition = QueryWrapperFactory.build();
        rolePermissionQueryCondition.select("permission_id");
        rolePermissionQueryCondition.in("role_id", roleIds);
        // 查询角色对应权限
        List<Object> roleBasePermissionResults = rolePermissionDAO.selectObjs(rolePermissionQueryCondition);

        // 查询权限
        QueryWrapper<AmPermissionPO> permissionQueryCondition = QueryWrapperFactory.build();
        permissionQueryCondition.in("id", roleBasePermissionResults);
        permissionQueryCondition.orderByAsc("sort");
        List<AmPermissionPO> permissionResults = permissionDAO.selectList(permissionQueryCondition);

        // 转化成菜单
        List<AmPermissionPO> rootNode = permissionResults.stream().filter(item -> item.getParentId() == 0).collect(Collectors.toList());
        return this.transferToMenu(rootNode, permissionResults);
    }

    /**
     * 权限转化为菜单
     *
     * @param rootNode  根节点
     * @param allResult 所有结果
     * @return 菜单
     */
    public List<MenuVO> transferToMenu(List<AmPermissionPO> rootNode, List<AmPermissionPO> allResult) {
        ArrayList<MenuVO> result = new ArrayList<>(rootNode.size());

        for (int i = 0; i < rootNode.size(); i++) {
            AmPermissionPO permissionItem = rootNode.get(i);
            MenuVO item = new MenuVO();
            item.setName(permissionItem.getName());
            item.setPath(permissionItem.getPath());
            item.setIcon(permissionItem.getIcon());
            item.setChildren(this.listMenuChildren(permissionItem.getId(), allResult));
            result.add(item);
        }

        return result;
    }

    /**
     * 递归处理菜单
     *
     * @param parentId  父节点
     * @param allResult 所有结果
     * @return 菜单
     */
    public List<MenuVO> listMenuChildren(Long parentId, List<AmPermissionPO> allResult) {
        ArrayList<MenuVO> result = new ArrayList<>();
        for (int i = 0; i < allResult.size(); i++) {
            AmPermissionPO permissionItem = allResult.get(i);
            if (Objects.equals(permissionItem.getParentId(), parentId)) {
                MenuVO item = new MenuVO();
                item.setName(permissionItem.getName());
                item.setPath(permissionItem.getPath());
                item.setIcon(permissionItem.getIcon());
                item.setChildren(this.listMenuChildren(permissionItem.getId(), allResult));
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public List<RouterVO> listRouterByRoleIds(List<Long> roleIds) {
        QueryWrapper<AmRolePermissionPO> rolePermissionQueryCondition = QueryWrapperFactory.build();
        rolePermissionQueryCondition.select("permission_id");
        rolePermissionQueryCondition.in("role_id", roleIds);
        // 查询角色对应权限
        List<Object> roleBasePermissionResults = rolePermissionDAO.selectObjs(rolePermissionQueryCondition);

        // 查询权限
        QueryWrapper<AmPermissionPO> permissionQueryCondition = QueryWrapperFactory.build();
        permissionQueryCondition.in("id", roleBasePermissionResults);
        permissionQueryCondition.orderByAsc("sort");
        List<AmPermissionPO> permissionResults = permissionDAO.selectList(permissionQueryCondition);

        // 由于路由的特性，直接转化为路由，不递归处理父级
        return permissionBeanMapping.fromPoToRouterVo(permissionResults);
    }

    @Override
    public PermissionVO getPermissionById(Long id) {
        AmPermissionPO permissionResult = permissionDAO.selectOne(QueryWrapperFactory.build(id));
        return permissionBeanMapping.fromPoToPermissionVo(permissionResult);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer modifyPermissionById(Long id, ModifyPermissionDTO modifyPermissionDTO) {
        // 数据转换
        AmPermissionPO entity = permissionBeanMapping.fromModifyDtoToPo(modifyPermissionDTO);
        // 判断是否有根节点
        Long parentId = entity.getParentId();
        if (parentId != null) {
            // 填充父节点
            if (parentId != 0) {
                String parentNodesParentIds = this.getParentNodesParentIds(parentId);
                entity.setParentIds(parentNodesParentIds);
            } else {
                entity.setParentIds(ROOT_STRING_ID);
            }
        }
        // 更新数据到数据库中
        return permissionDAO.update(entity, QueryWrapperFactory.build(id));
    }

    @Override
    public List<UiCascaderOptionVO> listPermissionsAsCascaderByParentId(Long parentId) {
        QueryWrapper<AmPermissionPO> condition = QueryWrapperFactory.build("parent_id", parentId);

        List<AmPermissionPO> permissions = permissionDAO.selectList(condition);

        List<UiCascaderOptionVO> cascaderOptions = permissionBeanMapping.fromPoToUiCascaderOptionVo(permissions);

        // 检查是否有根节点
        for (int i = 0; i < cascaderOptions.size(); i++) {

            UiCascaderOptionVO option = cascaderOptions.get(i);

            String id = option.getValue();

            // 查询根节点存在数量
            QueryWrapper<AmPermissionPO> childrenCheckCondition = QueryWrapperFactory.build("parent_id", id);
            Long children = permissionDAO.selectCount(childrenCheckCondition);
            option.setLeaf(children == 0);
        }
        return cascaderOptions;
    }

    @Override
    public List<PermissionTreeVO> listPermissionTree(Long parentId) {
        List<AmPermissionPO> databaseResult = permissionDAO.selectList(QueryWrapperFactory.build("parent_id", parentId));
        List<PermissionTreeVO> permissionTree = permissionBeanMapping.fromPoToTreeVo(databaseResult);
        for (int i = 0; i < permissionTree.size(); i++) {
            PermissionTreeVO permissionLeaf = permissionTree.get(i);
            Long childrenCount = permissionDAO.selectCount(QueryWrapperFactory.build("parent_id", permissionLeaf.getId()));
            permissionLeaf.setHasChildren(childrenCount > 0);
        }
        return permissionTree;
    }
}
