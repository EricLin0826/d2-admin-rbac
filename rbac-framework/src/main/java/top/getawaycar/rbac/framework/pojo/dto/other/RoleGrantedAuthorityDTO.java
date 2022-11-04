package top.getawaycar.rbac.framework.pojo.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author EricJeppesen
 * @date 2022/11/2 17:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleGrantedAuthorityDTO implements GrantedAuthority {

    private String role;

    private String description;

    @Override
    public String getAuthority() {
        return role;
    }
}
