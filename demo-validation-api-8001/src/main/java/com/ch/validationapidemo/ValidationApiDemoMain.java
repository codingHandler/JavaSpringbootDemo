package com.ch.validationapidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ch.**"})
public class ValidationApiDemoMain {

    public static void main(String[] args) {
        SpringApplication.run(ValidationApiDemoMain.class, args);
    }

}
