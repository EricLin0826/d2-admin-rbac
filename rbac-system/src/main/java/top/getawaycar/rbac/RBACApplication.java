package top.getawaycar.rbac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RBAC应用入口
 *
 * @author EricJeppesen
 * @date 2022/10/14 19:52
 */
@SpringBootApplication
@MapperScan(basePackages = {"top.getawaycar.rbac.common.dao"})
public class RBACApplication {

    public static void main(String[] args) {
        SpringApplication.run(RBACApplication.class);
    }

}
