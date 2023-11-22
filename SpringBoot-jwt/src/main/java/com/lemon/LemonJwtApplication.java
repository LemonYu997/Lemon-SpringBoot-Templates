package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LemonJwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(LemonJwtApplication.class, args);
        System.out.println("JWT应用启动成功");
    }
}
