package org.example.carsellingservice.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.example.carsellingservice.domain.view.Views;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Модель машины
 */
@Entity
@Table(name = "models")
@Getter
@Setter
public class Model implements Serializable {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;

    /**
     * Название.
     */
    @JsonView(Views.Name.class)
    private String name;

    /**
     * Производитель данной модели.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    private Maker maker;
}
