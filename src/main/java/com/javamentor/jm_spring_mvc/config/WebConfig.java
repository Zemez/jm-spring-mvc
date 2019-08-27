package com.javamentor.jm_spring_mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.javamentor.jm_spring_mvc"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/signin").setViewName("auth/signin");
//        registry.addViewController("/signup").setViewName("user");
    }

}

//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "com.javamentor.jm_spring_mvc")
//public class ServletConfig {
//
//    @Bean
//    public ViewResolver viewResolver() {
//        return new InternalResourceViewResolver("/WEB-INF/view/", ".jsp");
//    }
//
//}
