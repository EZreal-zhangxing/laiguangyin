package com.zx.Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ImportResource("classpath:applicationContext.xml")
public class Application{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
