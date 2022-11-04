package top.getawaycar.rbac.framework.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import top.getawaycar.rbac.framework.pojo.dto.create.CreateAdminDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyAdminDTO;
import top.getawaycar.rbac.framework.pojo.dto.modify.ModifyAdminPasswordDTO;
import top.getawaycar.rbac.framework.pojo.dto.query.AdminPageQueryDTO;
import top.getawaycar.rbac.framework.pojo.vo.AdminDetailVO;
import top.getawaycar.rbac.framework.pojo.vo.AdminListVO;

/**
 * 后台用户接口
 *
 * @author EricJeppesen
 * @date 2022/11/2 17:15
 */
public interface IAdminService extends UserDetailsService {

    /**
     * 创建后台用户
     *
     * @param createAdminDTO 创建后台用户数据创建类
     * @return 新用户标识
     */
    Long createAdmin(CreateAdminDTO createAdminDTO);

    /**
     * 修改后台用户信息
     *
     * @param id             后台用户标识
     * @param modifyAdminDTO 需要修改的信息
     * @return {@link Integer} 数据库受影响行数
     */
    Integer modifyAdmin(Long id, ModifyAdminDTO modifyAdminDTO);

    /**
     * 查询后台用户（分页）
     *
     * @param pageQueryDTO（分页）
     * @return {@link AdminListVO} 分页视图
     */
    Page<AdminListVO> listAdminByCondition(AdminPageQueryDTO pageQueryDTO);

    /**
     * 根据标识获取后台用户详情
     *
     * @param id 后台用户标识
     * @return {@link AdminDetailVO} 详情视图
     */
    AdminDetailVO getAdmin(Long id);

    /**
     * 修改后台用户锁定状态
     *
     * @param id               后台用户标识
     * @param targetLockStatus 目标状态
     * @return {@link Integer} 数据库受影响行数
     */
    Integer modifyAdminLockStatus(Long id, Integer targetLockStatus);

    /**
     * 修改后台用户密码
     *
     * @param id                     标识
     * @param modifyAdminPasswordDTO 新密码，确认密码
     * @return {@link Integer} 数据库受影响行数
     */
    Integer modifyAdminPassword(Long id, ModifyAdminPasswordDTO modifyAdminPasswordDTO);

}
