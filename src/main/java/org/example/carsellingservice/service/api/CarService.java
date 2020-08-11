package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Сервис для работы с машиными.
 */
public interface CarService {

    /**
     * Возвращает все машины.
     * @return Возвращённые машины.
     */
    Iterable<Car> getAll();

    /**
     * Сохраняет машину, добавленную пользователем.
     * @param user - пользователь, добавивший машину.
     * @param modelName - названи модели машины.
     * @param makerName - название марки машины.
     * @param price - цены машины.
     * @param yearOfProduction - год производства машины.
     * @param transmission - тип коробки передач.
     * @param engineType - тип двигателя.
     * @param file - фото машины.
     */
    void addCarForUser(
            User user, String modelName, String makerName, int price,
            int yearOfProduction, String transmission, String engineType, MultipartFile file
    ) throws IOException;

    /**
     * Ищет машины по набору параметров.
     * @param manufacturer - название марки машины.
     * @param model - название модели машины.
     * @param priceFrom - нижний ценовой диапазон.
     * @param priceTo - верхний ценовой диапазон.
     * @param yearFrom - нижний диапазон года производсва.
     * @param yearTo - верхний диапазон года производсва.
     * @param transmission - тип коробки передач.
     * @param engineType - тип двигателя.
     * @return Найденные машны.
     */
    Iterable<Car> findCars(
            String manufacturer, String model, Integer priceFrom, Integer priceTo, Integer yearFrom,
            Integer yearTo, List<String> transmission, List<String> engineType
    );

    /**
     * Возвращает максимальную цену машины.
     * @return Максимальная цена машины.
     */
    Integer getMaximumCarPrice();

    /**
     * Возвращает машины, добавленные пользователем с данным id.
     * @param id - id пользователя.
     * @return - Машины, добавленные пользователем.
     */
    Iterable<Car> getCarsOfUserWithId(String id);


    /**
     * Удаляет машину, добавленную пользователем.
     * @param id - id машины.
     * @param user - пользователь, добавивший машину.
     */
    void deleteCarWithId(Long id, User user);

    /**
     * Обновляет поля машины, добавленной пользователем.
     * @param car - машина.
     * @param user - пользователь, добавивший машину.
     */
    void updateCar(Car car, User user);
}
