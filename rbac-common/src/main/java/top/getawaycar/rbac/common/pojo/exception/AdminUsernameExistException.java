package top.getawaycar.rbac.common.pojo.exception;

/**
 * 后台用户已存在异常
 *
 * @author EricJeppesen
 * @date 2022/11/3 21:44
 */
public class AdminUsernameExistException extends GetawayCarException {

    public AdminUsernameExistException() {
        super(5003, "用户名已存在");
    }
}
