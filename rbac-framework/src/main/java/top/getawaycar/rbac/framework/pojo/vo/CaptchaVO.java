package top.getawaycar.rbac.framework.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 验证码视图
 *
 * @author EricJeppesen
 * @date 2022/11/2 11:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaptchaVO {

    /**
     * 标识
     */
    private String id;

    /**
     * Base64数据
     */
    private String captchaBase64;

}
