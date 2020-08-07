package org.example.carsellingservice.domain;

import com.fasterxml.jackson.annotation.JsonView;
import org.example.carsellingservice.domain.view.Views;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "makers")
public class Maker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private int id;
    @JsonView(Views.Name.class)
    private String name;
    @OneToMany(mappedBy = "maker", cascade = CascadeType.REMOVE)
    private Set<Model> models;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Model> getModels() {
        return models;
    }

    public void setModels(Set<Model> models) {
        this.models = models;
    }
}
