package com.musinsa.url.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/v1/short-urls");
        registry.addViewController("/v1/short-urls").setViewName("short-url-form.html");
        registry.addViewController("/v1/short-urls/table").setViewName("short-url-table.html");
    }
}
