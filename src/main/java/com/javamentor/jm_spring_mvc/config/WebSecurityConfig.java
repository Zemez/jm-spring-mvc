package com.javamentor.jm_spring_mvc.config;

import com.javamentor.jm_spring_mvc.handler.AccessDeniedHandlerImpl;
import com.javamentor.jm_spring_mvc.handler.AuthenticationFailureHandlerImpl;
import com.javamentor.jm_spring_mvc.handler.AuthenticationSuccessHandlerImpl;
import com.javamentor.jm_spring_mvc.handler.LogoutSuccessHandlerImpl;
import com.javamentor.jm_spring_mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.logging.Logger;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = Logger.getLogger(WebSecurityConfig.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandlerImpl();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandlerImpl();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandlerImpl();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/user/**").hasRole("USER")
                .and()
                .authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/signin") // Specifies the login page URL
                .loginProcessingUrl("/signin")
                .successHandler(authenticationSuccessHandler())
//                    .defaultSuccessUrl("/user")   // URL, where user will go after authenticating successfully.
                .failureHandler(authenticationFailureHandler())
//                    .failureUrl("/login?error")   // URL, where user will go after authentication failure.
                .permitAll() // Allow access to any URL associate to formLogin()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
//                    .logoutUrl("/signout")   // Specifies the logout URL, default URL is '/logout'
                .logoutSuccessHandler(logoutSuccessHandler())
//                    .logoutSuccessUrl("/login") // URL, where user will be redirect after successful
                .permitAll() // Allow access to any URL associate to logout()
                .and()
                .rememberMe().rememberMeParameter("remember");
    }

}
