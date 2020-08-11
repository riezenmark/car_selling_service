package org.example.carsellingservice.service.impl;

import org.example.carsellingservice.domain.*;
import org.example.carsellingservice.repository.CarMakerRepository;
import org.example.carsellingservice.repository.CarModelRepository;
import org.example.carsellingservice.repository.CarRepository;
import org.example.carsellingservice.repository.UserDetailsRepository;
import org.example.carsellingservice.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Реализация сервиса для работы с автомобилями.
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
@Service
public class CarCRUD implements CarService {

    /**
     * Путь для загрузки фото автомобилей.
     */
    @Value("${upload.path}")
    private String uploadPath;

    /**
     * Хранилище машин.
     */
    private final CarRepository carRepository;
    /**
     * Хранилище марок машин.
     */
    private final CarMakerRepository makerRepository;
    /**
     * Хранилище моделей машин.
     */
    private final CarModelRepository modelRepository;
    /**
     * Хранилище пользователей (для связи машин с конкетным пользователем).
     */
    private final UserDetailsRepository userRepository;

    @Autowired
    public CarCRUD(
            CarRepository carRepository, CarMakerRepository makerRepository,
            CarModelRepository modelRepository, UserDetailsRepository userRepository
    ) {
        this.carRepository = carRepository;
        this.makerRepository = makerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
    }

    /**
     * Возвращает все машины.
     * @return Возвращённые машины.
     */
    @Override
    public Iterable<Car> getAll() {
        return carRepository.findAll();
    }

    /**
     * Находит машины, подходящие по набору параметров.
     * Для этого сначала ищет машины по производителю, а после фильтрует список найденных машин в потоке.
     * При работе с большим количеством данных фильтрацию следует заменить на составные запросы.
     * @param manufacturer - название марки машины.
     * @param model - название модели машины.
     * @param priceFrom - нижний ценовой диапазон.
     * @param priceTo - верхний ценовой диапазон.
     * @param yearFrom - нижний диапазон года производсва.
     * @param yearTo - верхний диапазон года производсва.
     * @param transmission - тип коробки передач.
     * @param engineType - тип двигателя.
     * @return Найденные машины (или все машины, если производитель не указан).
     */
    @Override
    public Iterable<Car> findCars(
            String manufacturer, String model, Integer priceFrom, Integer priceTo,
            Integer yearFrom, Integer yearTo, List<String> transmission, List<String> engineType) {
        List<Car> cars;
        if (manufacturer != null && !manufacturer.equals("")) {
            cars = getListFromIterable(carRepository.findByMaker(makerRepository.findByName(manufacturer)));
            cars = this.filterCars(cars, model, priceFrom, priceTo, yearFrom, yearTo, transmission, engineType);
        } else {
            cars = getListFromIterable(this.getAll());
        }
        return cars;
    }

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
    @Override
    public void addCarForUser(
            User user, String modelName, String makerName, int price, int yearOfProduction,
            String transmission, String engineType, MultipartFile file
    ) throws IOException {
        Maker maker = makerRepository.findByName(makerName);
        Model model = modelRepository.findByName(modelName);
        Car car = this.createNewCarWithFields(maker, model, price, yearOfProduction, transmission, engineType);
        this.uploadFileIfExists(car, file);
        this.addCarForUser(car, user);
        this.saveCarAndUser(car, user);
    }

    /**
     * Возвращает максимальную цену машины.
     * @return Максимальная цена машины.
     */
    @Override
    public Integer getMaximumCarPrice() {
        return carRepository.getMaximumCarPrice();
    }

    /**
     * Возвращает машины, добавленные пользователем с данным id.
     * @param id - id пользователя.
     * @return - Машины, добавленные пользователем.
     */
    @Override
    public Iterable<Car> getCarsOfUserWithId(String id) {
        User user = userRepository.findById(id).orElse(null);
        return carRepository.findByUser(user);
    }

    /**
     * Удаляет машину, добавленную пользователем.
     * @param id - id машины.
     * @param user - пользователь, добавивший машину.
     */
    @Override
    public void deleteCarWithId(Integer id, User user) {
        user = userRepository.findById(user.getId()).orElse(null);
        Car car = carRepository.findById(id).orElse(null);
        if (this.userHasCar(user, car)) {
            this.deleteFileIfExists(car.getFilename());
            this.removeCarWithIdFromUserCars(user, id);
            userRepository.save(user);
            carRepository.delete(car);
        }
    }

    /**
     * Обновляет поля машины, добавленной пользователем.
     * @param car - машина.
     * @param user - пользователь, добавивший машину.
     */
    @Override
    public void updateCar(Car car, User user) {
        user = userRepository.findById(user.getId()).orElse(null);
        if (this.userHasCar(user, car)) {
            Car carFromDatabase = carRepository.findById(car.getId()).orElse(null);
            if (carFromDatabase != null) {
                this.fillCarFields(carFromDatabase, car);
                carRepository.save(carFromDatabase);
            }
        }
    }

    /**
     * Преобразует возвращаемый репозиторием Iterable машин в List машин.
     * @param cars - Iterable машин.
     * @return List машин.
     */
    private List<Car> getListFromIterable(Iterable<Car> cars) {
        return StreamSupport.stream(cars.spliterator(), false).collect(Collectors.toList());
    }

    /**
     * Фильтрует найденные машины по параметрам.
     * @param cars - список машин.
     * @param model - название модели.
     * @param priceFrom - нижний ценовой диапазон.
     * @param priceTo - верхний ценовой диапазон.
     * @param yearFrom - нижний диапазон года производсва.
     * @param yearTo - верхний диапазон года производсва.
     * @param transmission - тип коробки передач.
     * @param engineType - тип двигателя.
     * @return Отфильтрованный список машин.
     */
    private List<Car> filterCars(
            List<Car> cars, String model, Integer priceFrom, Integer priceTo,
            Integer yearFrom, Integer yearTo, List<String> transmission, List<String> engineType
    ) {
        Range price = new Range(priceFrom, priceTo);
        Range year = new Range(yearFrom, yearTo);
        this.validateRange(price);
        this.validateRange(year);
        return this.filterCarsByModelIfExists(
                this.filterCarsByPrice(
                        this.filterCarsByYear(
                                this.filterCarsByTransmissionIfExists(
                                        this.filterCarsByEngineTypeIfExists(
                                                cars, engineType
                                        ), transmission
                                ), year
                        ), price
                ), model
        );
    }

    /**
     * Фильтрует список машин по модели, если она задана.
     * @param cars - список машин для фильтрации.
     * @param model - название модели.
     * @return Отфильтрованный список машин.
     */
    private List<Car> filterCarsByModelIfExists(List<Car> cars, String model) {
        if (model != null && !model.equals("")) {
            cars = cars.stream().filter(car -> car.getModel().getName().equals(model)).collect(Collectors.toList());
        }
        return cars;
    }

    /**
     * Устанавливает корректные значения диапазона.
     * @param range - диапазон.
     */
    private void validateRange(Range range) {
        if (range.from == null || range.from < 0) {
            range.from = 0;
        }
        if (range.to == null || range.to < 0) {
            range.to = 0;
        }
        if (range.from > range.to) {
            int tmp = range.from;
            range.from = range.to;
            range.to = tmp;
        }
    }

    /**
     * Фильтрует список машин по ценовому диапазону.
     * @param cars - список машин для фильтрации.
     * @param price - ценовой диапазон.
     * @return Отфильтрованный список машин.
     */
    private List<Car> filterCarsByPrice(List<Car> cars, Range price) {
        if (price.from != 0) {
            cars = cars.stream().filter(
                    car -> car.getPrice() >= price.from
            ).collect(Collectors.toList());
        }
        if (price.to != 0) {
            cars = cars.stream().filter(
                    car -> car.getPrice() <= price.to
            ).collect(Collectors.toList());
        }
        return cars;
    }

    /**
     * Фильтрует список машин по годовому диапазону.
     * @param cars - список машин для фильтрации.
     * @param year - годовой диапазон.
     * @return Отфильтрованный список машин.
     */
    private List<Car> filterCarsByYear(List<Car> cars, Range year) {
        if (year.from != 0) {
            cars = cars.stream().filter(
                    car -> car.getYearOfProduction() >= year.from
            ).collect(Collectors.toList());
        }
        if (year.to != 0) {
            cars = cars.stream().filter(
                    car -> car.getYearOfProduction() <= year.to
            ).collect(Collectors.toList());
        }
        return cars;
    }

    /**
     * Фильтрует список машин по типу коробки передач, если он задан.
     * @param cars - список машин для фильтрации.
     * @param transmission - тип коробки передач.
     * @return Отфильтрованный список машин.
     */
    private List<Car> filterCarsByTransmissionIfExists(List<Car> cars, List<String> transmission) {
        if (transmission != null && transmission.size() != 0) {
            cars = cars.stream().filter(
                    car -> transmission.contains(car.getTransmission().name())
            ).collect(Collectors.toList());
        }
        return cars;
    }

    /**
     * Фильтрует список машин по типу двигателя, если он задан.
     * @param cars - список машин для фильтрации.
     * @param engineType - тип двигателя.
     * @return Отфильтрованный список машин.
     */
    private List<Car> filterCarsByEngineTypeIfExists(List<Car> cars, List<String> engineType) {
        if (engineType != null && engineType.size() != 0) {
            cars = cars.stream().filter(
                    car -> engineType.contains(car.getEngineType().name())
            ).collect(Collectors.toList());
        }
        return cars;
    }

    /**
     * Загружает фото автомобиля, если оно задано.
     * @param car - машина.
     * @param file - фото машины.
     */
    private void uploadFileIfExists(Car car, MultipartFile file) throws IOException {
        if (file != null) {
            String filename = file.getOriginalFilename();
            if (filename != null && !filename.isEmpty()) {
                setFile(car, file);
            }
        }
    }

    /**
     * Добавляет машину к коллекции добавленных пользователем машин.
     * @param car - машина.
     * @param user - пользователь.
     */
    private void addCarForUser(Car car, User user) {
        Set<Car> userCars = user.getAddedCars();
        if (userCars == null) {
            userCars = new HashSet<>();
        }
        userCars.add(car);
    }

    /**
     * Сохраняет пользователя и машину в репозитории.
     * @param car - машина.
     * @param user - пользователь.
     */
    private void saveCarAndUser(Car car, User user) {
        user = userRepository.save(user);
        car.setUser(user);
        carRepository.save(car);
    }

    /**
     * Создаёт новую машину с полями.
     * @param maker - производитель.
     * @param model - модель.
     * @param price - цена.
     * @param yearOfProduction - год производства.
     * @param transmission - тип коробки передач.
     * @param engineType - тип двигателя.
     * @return Созданныя машина.
     */
    private Car createNewCarWithFields(
            Maker maker, Model model, int price, int yearOfProduction,
            String transmission, String engineType
    ) {
        Car car = new Car();
        car.setMaker(maker);
        car.setModel(model);
        car.setPrice(price);
        car.setYearOfProduction(yearOfProduction);
        car.setTransmission(Transmission.valueOf(transmission));
        car.setEngineType(EngineType.valueOf(engineType));
        return car;
    }

    /**
     * Проверяет, есть ли машина в коллекции машин пользователя.
     * @param user - пользователь.
     * @param car - машина.
     * @return - результат проверки.
     */
    private boolean userHasCar(User user, Car car) {
        return user != null && car != null && user.getAddedCars().contains(car);
    }

    /**
     * Удаляет файл с фото машины, если он существует.
     * @param filename - имя файла.
     */
    private void deleteFileIfExists(String filename) {
        File file = new File(uploadPath + "/" + filename);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Удаляет машину с заданным id из коллекции машин пользователя.
     * @param user - пользователь.
     * @param id - id машины.
     */
    private void removeCarWithIdFromUserCars(User user, Integer id) {
        Set<Car> usersCars = user.getAddedCars();
        usersCars = usersCars.stream().filter(c -> !c.getId().equals(id)).collect(Collectors.toSet());
        user.setAddedCars(usersCars);
    }

    /**
     * Сохраняет файл с фото в директорию загрузки и связывает его с машиной.
     * @param car - машина.
     * @param file - файл с фото.
     */
    private void setFile(Car car, MultipartFile file) throws IOException {
        this.makeUploadDirectoryIfNotExists();
        String uniqueFilename = UUID.randomUUID().toString() + "." + file.getOriginalFilename();
        file.transferTo(new File(uploadPath + "/" + uniqueFilename));
        car.setFilename(uniqueFilename);
    }

    /**
     * Создаёт директорию для загрузки файлов, если она не создана.
     */
    private void makeUploadDirectoryIfNotExists() {
        File uploadDirectory = new File(uploadPath);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdir();
        }
    }

    /**
     * Заполняет поля машины из базы данных новыми значениями.
     * @param carFromDatabase - машина из базы данных.
     * @param car - машина с новыми полями.
     */
    private void fillCarFields(Car carFromDatabase, Car car) {
        carFromDatabase.setMaker(makerRepository.findByName(car.getMaker().getName()));
        carFromDatabase.setModel(modelRepository.findByName(car.getModel().getName()));
        carFromDatabase.setPrice(car.getPrice());
        carFromDatabase.setYearOfProduction(car.getYearOfProduction());
        carFromDatabase.setEngineType(car.getEngineType());
        carFromDatabase.setTransmission(car.getTransmission());
    }

    /**
     * Диапазон (цен, лет производства).
     */
    private static final class Range {
        private Integer from;
        private Integer to;

        public Range(Integer from, Integer to) {
            this.from = from;
            this.to = to;
        }
    }
}
