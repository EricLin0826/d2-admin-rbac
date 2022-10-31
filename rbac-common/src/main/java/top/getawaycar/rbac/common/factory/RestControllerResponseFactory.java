package top.getawaycar.rbac.common.factory;

import top.getawaycar.rbac.common.pojo.vo.RestControllerResponseVO;

/**
 * RestController响应视图
 *
 * @author EricJeppesen
 * @date 2022/10/14 20:06
 */
public class RestControllerResponseFactory {

    /**
     * 创建成功的响应
     *
     * @param <T>  数据类型
     * @return 成功的响应
     */
    public static <T> RestControllerResponseVO<T> buildSuccessResponse() {
        return new RestControllerResponseVO<T>("操作成功", 2000, null);
    }

    /**
     * 创建成功的响应
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return 成功的响应
     */
    public static <T> RestControllerResponseVO<T> buildSuccessResponse(T data) {
        return new RestControllerResponseVO<T>("操作成功", 2000, data);
    }

    /**
     * 创建成功的响应
     *
     * @param code 响应代码
     * @param data 数据
     * @param <T>  数据类型
     * @return 成功的响应
     */
    public static <T> RestControllerResponseVO<T> buildSuccessResponse(Integer code, T data) {
        return new RestControllerResponseVO<T>("操作成功", code, data);
    }

    /**
     * 创建失败的响应
     *
     * @param <T> 数据类型
     * @return 失败的响应
     */
    public static <T> RestControllerResponseVO<T> buildErrorResponse() {
        return new RestControllerResponseVO<T>("系统内部错误", 5000, null);
    }

    /**
     * 创建失败的响应
     *
     * @param code 失败编码
     * @param <T>  数据类型
     * @return 失败的响应
     */
    public static <T> RestControllerResponseVO<T> buildErrorResponse(Integer code) {
        return new RestControllerResponseVO<T>("系统内部错误", code, null);
    }

    /**
     * 创建失败的响应
     *
     * @param code    失败编码
     * @param message 消息
     * @param <T>     失败的响应
     * @return 失败的响应
     */
    public static <T> RestControllerResponseVO<T> buildErrorResponse(Integer code, String message) {
        return new RestControllerResponseVO<T>(message, code, null);
    }

    /**
     * 创建失败的响应
     *
     * @param code    失败编码
     * @param message 消息
     * @param data    数据
     * @param <T>     失败的响应
     * @return 失败的响应
     */
    public static <T> RestControllerResponseVO<T> buildErrorResponse(Integer code, String message, T data) {
        return new RestControllerResponseVO<T>(message, code, data);
    }


}
