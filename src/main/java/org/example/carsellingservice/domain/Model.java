package org.example.carsellingservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import org.example.carsellingservice.domain.view.Views;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.IdName.class)
    private Long id;
    @JsonView(Views.IdName.class)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private Maker manufacturer;

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

    public Maker getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Maker manufacturer) {
        this.manufacturer = manufacturer;
    }
}
