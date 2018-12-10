package com.jwebcoder.grocerymain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableFeignClients
@EnableEurekaClient
@EnableCaching
public class GroceryMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroceryMainApplication.class, args);
    }
}
