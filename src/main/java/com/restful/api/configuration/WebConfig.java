package com.restful.api.configuration;

import com.restful.api.common.interceptor.LogInterceptor;
import com.restful.api.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor((new LogInterceptor()));
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/oldLogin");
    }
}
