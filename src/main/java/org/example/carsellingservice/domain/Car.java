package org.example.carsellingservice.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cars")
@NamedEntityGraph(name = "carWithMakerAndModel",
    attributeNodes = {
            @NamedAttributeNode("maker"),
            @NamedAttributeNode("model")
    })
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private CarMaker maker;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CarModel model;
    private Integer price;
    @Column(name = "year_of_production")
    private Integer yearOfProduction;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Enumerated(EnumType.STRING)
    @Column(name = "engine_type")
    private EngineType engineType;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
