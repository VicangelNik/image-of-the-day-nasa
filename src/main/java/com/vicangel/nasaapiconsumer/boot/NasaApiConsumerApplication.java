package com.vicangel.nasaapiconsumer.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.vicangel.nasaapiconsumer.*"})
public class NasaApiConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NasaApiConsumerApplication.class, args);
    }

}
