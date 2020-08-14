package org.example.carsellingservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "car_makers")
public class CarMaker {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "maker", orphanRemoval = true)
    private Set<CarModel> models;
}
