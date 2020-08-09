package org.example.carsellingservice.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.example.carsellingservice.domain.view.Views;

import javax.persistence.*;
import java.io.Serializable;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Maker getMaker() {
        return maker;
    }

    public void setMaker(Maker maker) {
        this.maker = maker;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
