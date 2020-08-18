package com.restful.api.configuration;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class FastJsonAutoConfiguration {
    @Bean
    public HttpMessageConverter<?> httpMessageConverter() {
        return new FastJsonHttpMessageConverter();
    }
}
