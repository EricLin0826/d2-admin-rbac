package top.getawaycar.rbac.framework.handler;

import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import top.getawaycar.rbac.framework.constant.HttpStatus;
import top.getawaycar.rbac.framework.pojo.dto.other.AdminAuthorizationDTO;
import top.getawaycar.rbac.framework.util.ServletUtil;
import top.getawaycar.rbac.framework.util.TokenUtil;
import top.getawaycar.rbac.mvc.factory.RestControllerResponseFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 *
 * @author EricJeppesen
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private TokenUtil tokenUtil;

    @Autowired
    public void setTokenService(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    /**
     * 退出处理
     *
     *
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        AdminAuthorizationDTO loginUser = tokenUtil.getLoginAdmin(request);
        if (loginUser != null) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenUtil.deleteLoginAdmin(loginUser.getToken());
        }
        ServletUtil.renderString(response, JSONUtil.toJsonStr(RestControllerResponseFactory.buildErrorResponse(HttpStatus.SUCCESS, "退出成功")));
    }
}
