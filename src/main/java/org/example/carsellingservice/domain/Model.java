package org.example.carsellingservice.domain;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.carsellingservice.domain.view.Views;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;
    @JsonView(Views.Name.class)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private Maker maker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Maker getMaker() {
        return maker;
    }

    public void setMaker(Maker manufacturer) {
        this.maker = manufacturer;
    }
}
