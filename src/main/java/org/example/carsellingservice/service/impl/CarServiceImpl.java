package org.example.carsellingservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.carsellingservice.domain.Car;
import org.example.carsellingservice.domain.EngineType;
import org.example.carsellingservice.domain.Transmission;
import org.example.carsellingservice.domain.User;
import org.example.carsellingservice.dto.CarDto;
import org.example.carsellingservice.repository.CarRepository;
import org.example.carsellingservice.service.api.CarModelService;
import org.example.carsellingservice.service.api.CarService;
import org.example.carsellingservice.service.util.CarMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarModelService modelService;
    private final CarMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<CarDto> getCars(
            final Integer makerId, final Long modelId, final Integer priceFrom,
            final Integer priceTo, final Integer yearFrom, final Integer yearTo,
            final List<Transmission> transmission, final List<EngineType> engineType
    ) {
        List<Car> carsFromRepository;
        if (Stream.of(makerId, modelId, priceFrom, priceTo, yearFrom, yearTo, transmission, engineType).anyMatch(Objects::nonNull)) {
            carsFromRepository = carRepository.findCarsByParameters(
                    makerId, modelId, priceFrom, priceTo, yearFrom, yearTo,
                    transmission, engineType);
        } else {
            carsFromRepository = carRepository.findAll();
        }
        return mapper.map(carsFromRepository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarDto> getCarsOfUserWithId(Long userId) {
        return mapper.map(carRepository.findByUser_Id(userId));
    }

    @Override
    @Transactional(readOnly = true)
    public CarDto getById(final Long id) {
        return mapper.map(carRepository.findByIdWithMakerAndModel(id).orElse(null));
    }

    @Override
    @Transactional
    public CarDto add(final Car car, final User user) {
        Car carFromRepository = null;
        if (carIsValid(car) && user != null) {
            car.setId(null);
            car.setUser(user);
            carFromRepository = carRepository.save(car);
        }
        return mapper.map(carFromRepository);
    }

    @Override
    @Transactional
    public CarDto update(final Long id, final Car car, final User user) {
        Car carFromRepository = carRepository.findByIdWithMakerAndModel(id).orElse(null);
        if (carFromRepository != null && carIsValid(car) && carBelongsToUser(car, user)) {
            car.setId(id);
            carFromRepository = carRepository.save(car);
        }
        return mapper.map(carFromRepository);
    }

    @Override
    @Transactional
    public void deleteById(final Long id, final User user) {
        if (carRepository.existsByIdAndUser_Id(id, user.getId())) {
            carRepository.deleteById(id);
        }
    }

    private boolean carFieldsAreValid(final Car car) {
        return car.getMaker() != null
                && car.getMaker().getId() != null
                && car.getModel() != null
                && car.getModel().getId() != null
                && car.getPrice() != null
                && car.getYearOfProduction() != null
                && car.getTransmission() != null
                && car.getEngineType() != null;
    }

    private boolean carIsValid(Car car) {
        return carFieldsAreValid(car)
                && modelService.modelBelongsToMakerWithId(car.getModel(), car.getMaker().getId());
    }

    private boolean carBelongsToUser(Car car, User user) {
        return user != null
                && car.getUser().getId().equals(user.getId());
    }
}
