package org.example.carsellingservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.example.carsellingservice.domain.view.Views;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Пользователь.
 */
@Entity
@Table(name = "users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Getter
@Setter
public class User implements Serializable {

    /**
     * Идентификатор.
     */
    @Id
    @JsonView(Views.Id.class)
    private String id;

    /**
     * Имя.
     */
    @JsonView(Views.Name.class)
    private String name;

    /**
     * Ссылка на аватар в Google.
     */
    private String userpic;

    /**
     * Электронная почта пользователя.
     */
    @JsonView(Views.Email.class)
    private String email;

    /**
     * Пол.
     */
    private String gender;

    /**
     * Локаль.
     */
    private String locale;

    /**
     * Машины, добавленные пользователем.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Car> addedCars;

    /**
     * Время последнего визита.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastVisit;
}
