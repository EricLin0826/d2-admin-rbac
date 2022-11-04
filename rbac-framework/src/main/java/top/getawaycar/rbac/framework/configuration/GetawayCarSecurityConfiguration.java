package top.getawaycar.rbac.framework.configuration;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.filter.CorsFilter;
import top.getawaycar.rbac.framework.filter.JwtAuthenticationTokenFilter;
import top.getawaycar.rbac.framework.service.IAdminService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 本地权限配置
 *
 * @author EricJeppesen
 * @date 2022/11/2 9:56
 */
@Configuration
@EnableWebSecurity
public class GetawayCarSecurityConfiguration {

    public static final String LOGIN_URL = "/login";
    public static final String ALL_URL_ANT = "/**";
    public static final String CAPTCHA_URL = "/captcha";

    public String[] customizeUrls;

    @Value(value = "${getawaycar.white.list:}")
    public String whiteList;

    @PostConstruct
    public void securityConfiguration() {
        if (StrUtil.isNotEmpty(whiteList)) {
            this.customizeUrls = whiteList.split(StrUtil.COMMA);
        } else {
            this.customizeUrls = new String[]{};
        }
    }


    private AuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    public void setUnauthorizedHandler(AuthenticationEntryPoint unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
    }

    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    public void setLogoutSuccessHandler(LogoutSuccessHandler logoutSuccessHandler) {
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        // 注意前后顺序
        // 先忽略、在要求所有信息校验
        List<String> ignoreUrls = new ArrayList<>(Arrays.asList(customizeUrls));
        ignoreUrls.add(LOGIN_URL);
        ignoreUrls.add(CAPTCHA_URL);
        // toArray方法会自动扩容
        String[] ignoreUrlsArray = ignoreUrls.toArray(new String[0]);

        http.csrf().disable()
                // 认证失败处理类
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 过滤请求
                .authorizeRequests()
                // 对于登录login 验证码captchaImage 允许匿名访问
                .antMatchers(ignoreUrlsArray).anonymous()
                .antMatchers(
                        HttpMethod.GET,
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.png",
                        "/**/*.js"
                ).permitAll()
                .antMatchers(
                        HttpMethod.POST,
                        "/**/**/upload"
                ).permitAll()
                .antMatchers("/file/manage/upload").anonymous()
                .antMatchers("/template/**").anonymous()
                .antMatchers("/profile/**").anonymous()
                .antMatchers("/common/download**").anonymous()
                .antMatchers("/common/download/resource**").anonymous()
                .antMatchers("/swagger-ui.html").anonymous()
                .antMatchers("/swagger-resources/**").anonymous()
                .antMatchers("/webjars/**").anonymous()
                .antMatchers("/*/api-docs").anonymous()
                .antMatchers("/druid/**").anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and().authenticationManager(authenticationManager)
                .headers().frameOptions().disable();
        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
        // 添加JWT filter
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加CORS filter
        http.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
        http.addFilterBefore(corsFilter, LogoutFilter.class);
        return http.build();
    }


    private JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Autowired
    public void setAuthenticationTokenFilter(JwtAuthenticationTokenFilter authenticationTokenFilter) {
        this.authenticationTokenFilter = authenticationTokenFilter;
    }

    private CorsFilter corsFilter;

    @Autowired
    public void setCorsFilter(CorsFilter corsFilter) {
        this.corsFilter = corsFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
