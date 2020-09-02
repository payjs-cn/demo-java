package com.payjs.pay.web;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author chengtianqi
 * @create 2020/6/14 15:07
 */
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 页面跨域访问Controller过滤
     *
     * @return
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("POST","GET")
                .allowedOrigins("*");
    }

}
