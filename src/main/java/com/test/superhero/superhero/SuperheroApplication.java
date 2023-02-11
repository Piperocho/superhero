package com.test.superhero.superhero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.test.superhero.superhero.service", "com.test.superhero.superhero.controller"})
public class SuperheroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperheroApplication.class, args);
    }

}
