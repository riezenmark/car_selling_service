package org.example.carsellingservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private CarMaker maker;
    @ManyToOne
    private CarModel model;
    private Integer price;
    @Column(name = "year_of_production")
    private Integer yearOfProduction;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Enumerated(EnumType.STRING)
    @Column(name = "engine_type")
    private EngineType engineType;
    private String filename;
    @ManyToOne
    private User user;
}
