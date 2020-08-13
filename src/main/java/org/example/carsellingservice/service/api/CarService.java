package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {

    //todo pageable?
    List<Car> getAll();

    //todo refactor
    void addCarForUser(
            User user, String modelName, String makerName, int price,
            int yearOfProduction, String transmission, String engineType, MultipartFile file
    ) throws IOException;

    List<Car> findCars(
            String manufacturer, String model, Integer priceFrom, Integer priceTo, Integer yearFrom,
            Integer yearTo, List<String> transmission, List<String> engineType
    );

    Integer getMaximumCarPrice();

    List<Car> getCarsOfUserWithId(String id);

    void deleteCarWithId(Long id, User user);

    void updateCar(Car car, User user);
}
