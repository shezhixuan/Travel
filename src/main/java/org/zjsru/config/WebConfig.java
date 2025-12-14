package org.zjsru.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射HTML文件到根路径
        registry.addResourceHandler("/*.html")
                .addResourceLocations("classpath:/static/pages/");

        // 映射pages目录下的所有资源
        registry.addResourceHandler("/pages/**")
                .addResourceLocations("classpath:/static/pages/");

        // 映射JS文件
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");

        // 映射CSS文件
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");

        // 映射图片文件
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
    }
}

