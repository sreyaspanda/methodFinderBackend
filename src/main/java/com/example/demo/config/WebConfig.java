package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow cross-origin requests from all domains for this specific endpoint
        registry.addMapping("/**")
                .allowedOrigins("*") // Change * to specific origins if needed
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}

