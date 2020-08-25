package org.example.carsellingservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "car_makers")
public class CarMaker implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "maker")
    private Set<CarModel> models;
}
