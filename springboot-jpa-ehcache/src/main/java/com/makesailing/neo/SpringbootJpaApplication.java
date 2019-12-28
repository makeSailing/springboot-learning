package com.makesailing.neo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // 开启缓存注解
public class SpringbootJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }
}
