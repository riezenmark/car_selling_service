package org.example.carsellingservice.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.example.carsellingservice.domain.view.Views;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "cars")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;
    @JsonView(Views.IdName.class)
    @ManyToOne(fetch = FetchType.EAGER)
    private Maker maker;
    @JsonView(Views.IdName.class)
    @ManyToOne(fetch = FetchType.EAGER)
    private Model model;
    @JsonView(Views.Name.class)
    private int price;
    @JsonView(Views.Name.class)
    private int yearOfProduction;
    @Enumerated(EnumType.STRING)
    @JsonView(Views.Name.class)
    private Transmission transmission;
    @Enumerated(EnumType.STRING)
    @JsonView(Views.Name.class)
    private EngineType engineType;
    @JsonView(Views.Name.class)
    private String filename;
    @JsonView(Views.IdNameEmail.class)
    @ManyToOne
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
