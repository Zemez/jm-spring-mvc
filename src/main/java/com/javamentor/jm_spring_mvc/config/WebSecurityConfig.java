package com.javamentor.jm_spring_mvc.config;

import com.javamentor.jm_spring_mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                .successHandler((req, res, auth) -> {    //Success handler invoked after successful authentication
                    req.getSession().setAttribute("user", userService.find(auth.getName()));
                    req.getSession().setAttribute("message", "You are logged in successfully.");
                    logger.info(auth.getAuthorities().toString());
                    if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                        res.sendRedirect("/admin");
                    } else {
                        res.sendRedirect("/user");
                    }
                })
//                    .defaultSuccessUrl("/user")   // URL, where user will go after authenticating successfully.
                .failureHandler((req, res, exp) -> {  // Failure handler invoked after authentication failure
                    if (exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
                        req.getSession().setAttribute("error", "Invalid username or password.");
                    } else {
                        req.getSession().setAttribute("error", exp.getMessage());
                    }
                    res.sendRedirect("/signin"); // Redirect user to login page with error message.
                })
//                    .failureUrl("/login?error")   // URL, where user will go after authentication failure.
                .permitAll() // Allow access to any URL associate to formLogin()
                .and()
                .exceptionHandling().accessDeniedHandler((req, res, exp) -> {
            req.getSession().setAttribute("error", "Access is denied: " + req.getRequestURI());
            res.sendRedirect("/signin");
        })
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
//                    .logoutUrl("/signout")   // Specifies the logout URL, default URL is '/logout'
                .logoutSuccessHandler((req, res, auth) -> {   // Logout handler called after successful logout
                    req.getSession().setAttribute("message", "You are logged out successfully.");
                    res.sendRedirect("/"); // Redirect user to login page with message.
                })
//                    .logoutSuccessUrl("/login") // URL, where user will be redirect after successful
                .permitAll() // Allow access to any URL associate to logout()
                .and()
                .rememberMe().rememberMeParameter("remember");
    }

}
