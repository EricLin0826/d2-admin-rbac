package top.getawaycar.rbac.mvc.configuration;


import cn.hutool.core.exceptions.StatefulException;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import top.getawaycar.rbac.common.pojo.exception.GetawayCarException;
import top.getawaycar.rbac.mvc.factory.RestControllerResponseFactory;
import top.getawaycar.rbac.mvc.pojo.vo.RestControllerResponseVO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 响应-切面
 *
 * @author EricJeppesen
 * @date 2022/10/14 19:53
 */
@RestControllerAdvice(basePackages = "top.getawaycar.rbac")
@Slf4j
public class GetawayCarHttpResponseAspect implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o != null) {
            if (o instanceof String) {
                return JSONUtil.toJsonStr(RestControllerResponseFactory.buildSuccessResponse((String) o));
            } else if (o instanceof RestControllerResponseVO) {
                return o;
            } else {
                return RestControllerResponseFactory.buildSuccessResponse(o);
            }
        } else {
            return RestControllerResponseFactory.buildSuccessResponse();
        }
    }

    public static final Pattern CHINESE_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

    @ExceptionHandler
    @ResponseBody
    public Object errorHandler(Exception e) {
        RestControllerResponseVO<Object> errorResponse = RestControllerResponseFactory.buildErrorResponse(5000, "服务器内部错误，请联系管理人员！");
        try {
            e.printStackTrace();
            Matcher m = CHINESE_PATTERN.matcher(e.getMessage());
            if (e instanceof GetawayCarException) {
                GetawayCarException getawayCarException = (GetawayCarException) e;
                errorResponse = RestControllerResponseFactory.buildErrorResponse(getawayCarException.getStatus(), e.getMessage());
            } else if (e instanceof StatefulException) {
                StatefulException statefulException = (StatefulException) e;
                errorResponse = RestControllerResponseFactory.buildErrorResponse(statefulException.getStatus(), e.getMessage());
            } else {
                // 如果异常中有中文存在，则用Exception的message
                if (m.find()) {
                    errorResponse = RestControllerResponseFactory.buildErrorResponse(5000, e.getMessage());
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            errorResponse = RestControllerResponseFactory.buildErrorResponse(5000, "服务器内部错误，请联系管理人员！");
        }
        return errorResponse;
    }
}
