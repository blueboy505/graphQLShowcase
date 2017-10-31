package com.example.southpark.graphqlsouthparkdemo.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class Episode {

    @Id
    private String id;
    private String name;
    private Date airDate;

    @Column(length = 5000)
    private String description;

    @ManyToOne
    @JoinColumn(name="season.id", nullable = false)
    private Season season;

    public com.example.southpark.graphqlsouthparkdemo.model.Episode toModel() {
        com.example.southpark.graphqlsouthparkdemo.model.Episode model = new com.example.southpark.graphqlsouthparkdemo.model.Episode();
        model.setAirDate(this.airDate);
        model.setName(this.name);
        model.setId(this.id);
        model.setSeason(season.toSimpleModel());
        model.setDescription(this.getDescription());
        return model;
    }
}
