package org.example.carsellingservice.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.example.carsellingservice.domain.view.Views;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "models")
@Getter
@Setter
public class Model implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;
    @JsonView(Views.Name.class)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private Maker maker;
}
