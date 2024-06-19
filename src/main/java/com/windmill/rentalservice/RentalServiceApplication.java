package com.windmill.rentalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
@EnableScheduling
public class RentalServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(RentalServiceApplication.class, args);
    }

}
