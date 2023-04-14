package com.etoak.commons.config;

import com.etoak.commons.filter.CrossFilter;
import com.etoak.commons.properties.ImageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    ImageProperties imageProperties;

//    @Autowired
//    AuthIntercepter authIntercepter;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        String mapping = imageProperties.getPrefix().endsWith("/") ?
//                imageProperties.getPrefix() + "**" : imageProperties.getPrefix() + "/**";

//        registry.addInterceptor(authIntercepter)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login","/code",mapping);
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String mapping = imageProperties.getPrefix().endsWith("/") ?
                imageProperties.getPrefix() + "**" : imageProperties.getPrefix() + "/**";

        String location = "file:" + imageProperties.getDir() +
                (imageProperties.getDir().endsWith("/") ? "" : "/");

        registry.addResourceHandler(mapping).addResourceLocations(location);
    }

    @Bean
    public FilterRegistrationBean<CrossFilter> crossFilterBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CrossFilter());
        registrationBean.addUrlPatterns("*");

        registrationBean.setOrder(-200);
        return registrationBean;
    }
}
