package org.example.carsellingservice.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.example.carsellingservice.domain.view.Views;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Марка машины.
 */
@Entity
@Table(name = "makers")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Getter
@Setter
public class Maker implements Serializable {

    /**
     * Иденификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Integer id;

    /**
     * Название.
     */
    @JsonView(Views.Name.class)
    private String name;

    /**
     * Модели данной марки.
     */
    @OneToMany(mappedBy = "maker", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Model> models;
}
