package com.liwei.paytemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PayTemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayTemplateApplication.class, args);
    }
}
