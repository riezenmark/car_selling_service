package org.example.carsellingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//todo разобраться с "open session in view"
@SpringBootApplication
public class CarSellingApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(CarSellingApp.class, args);
    }
}
