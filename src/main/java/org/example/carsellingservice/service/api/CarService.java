package org.example.carsellingservice.service.api;

import org.example.carsellingservice.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CarService {

    void addCarForUser(
            User user, String modelName, String makerName, int price,
            int yearOfProduction, String transmission, String engineType, MultipartFile file
    ) throws IOException;
}
