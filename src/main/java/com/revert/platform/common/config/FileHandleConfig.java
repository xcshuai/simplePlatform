package com.revert.platform.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * xiecong
 */

@Configuration
public class FileHandleConfig extends WebMvcConfigurerAdapter {

    @Value("${platform.file.upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** addResourceHandler 增加访问前缀
         *  addResourceLocations 静态资源路径
         * */
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + uploadPath+"//");
        super.addResourceHandlers(registry);
    }
}
