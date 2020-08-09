package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {

    Iterable<Car> getAll();

    void addCarForUser(
            User user, String modelName, String makerName, int price,
            int yearOfProduction, String transmission, String engineType, MultipartFile file
    ) throws IOException;

    Iterable<Car> findCars(
            String manufacturer, String model, Integer priceFrom, Integer priceTo, Integer yearFrom,
            Integer yearTo, List<String> transmission, List<String> engineType
    );

    Integer getMaximumCarPrice();

    Iterable<Car> getCarsOfUserWithId(String id);

    void deleteCarWithId(Long id, User user);
}
