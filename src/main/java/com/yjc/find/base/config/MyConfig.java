package com.yjc.find.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyConfig extends WebMvcConfigurationSupport {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        //registry.addResourceHandler("/image/**").addResourceLocations("file:/usr/myfiles/images/");
        registry.addResourceHandler("/images/**").addResourceLocations("file:D:/work/find/images/");
    }
}
