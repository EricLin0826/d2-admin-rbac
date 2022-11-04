package top.getawaycar.rbac.mvc.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * RestController通用返回类
 *
 * @author EricJeppesen
 * @date 2022/10/14 20:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RestControllerResponseVO<T> {

    /**
     * 消息
     */
    private String message;

    /**
     * 编码
     */
    private Integer code;

    /**
     * 数据
     */
    private T data;
}
