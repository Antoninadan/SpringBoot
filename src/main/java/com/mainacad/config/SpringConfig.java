package com.mainacad.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Value("${spring.datasource.url}")
    private String dbURL;
}