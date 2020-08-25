package org.example.carsellingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Пример web-приложения для сервиса по продаже автомобилей.
 *
 * @author riezenmark@gmail.com
 * @version 0.0.1
 */
@SpringBootApplication
public class CarSellingApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(CarSellingApp.class, args);
    }
}
