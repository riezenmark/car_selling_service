package org.example.carsellingservice.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.example.carsellingservice.domain.CarModel;
import org.example.carsellingservice.domain.EngineType;
import org.example.carsellingservice.domain.Transmission;
import org.example.carsellingservice.domain.User;

@Data
public class CarDto {
    private Long id;
    private CarMakerDto maker;
    private CarModel model;
    private Integer price;
    private Integer yearOfProduction;
    private Transmission transmission;
    private EngineType engineType;
    private User user;
}
