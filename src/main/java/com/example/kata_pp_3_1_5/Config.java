package com.example.kata_pp_3_1_5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
