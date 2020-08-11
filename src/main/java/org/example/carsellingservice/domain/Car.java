package org.example.carsellingservice.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.example.carsellingservice.domain.view.Views;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Машина.
 */
@Entity
@Getter
@Setter
@Table(name = "cars")
public class Car implements Serializable {

    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;

    /**
     * Марка машины.
     */
    @JsonView(Views.IdName.class)
    @ManyToOne(fetch = FetchType.EAGER)
    private Maker maker;

    /**
     * Модель машины.
     */
    @JsonView(Views.IdName.class)
    @ManyToOne(fetch = FetchType.EAGER)
    private Model model;

    /**
     * Цена.
     */
    @JsonView(Views.Name.class)
    private int price;

    /**
     * Год производства.
     */
    @JsonView(Views.Name.class)
    private int yearOfProduction;

    /**
     * Коробка передач.
     */
    @Enumerated(EnumType.STRING)
    @JsonView(Views.Name.class)
    private Transmission transmission;

    /**
     * Тип двигателя.
     */
    @Enumerated(EnumType.STRING)
    @JsonView(Views.Name.class)
    private EngineType engineType;

    /**
     * Имя файла с фото.
     */
    @JsonView(Views.Name.class)
    private String filename;

    /**
     * Пользователь, добавивший машину.
     */
    @JsonView(Views.IdNameEmail.class)
    @ManyToOne
    private User user;

    /**
     * Сравнивает машины по id.
     * @param o - Объект для сравнения.
     * @return - Результат сравнения. True - объекты равны, false - объекты не равны.
     */
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

    /**
     * Хеширует машины по id.
     * @return - Хеш по id.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
