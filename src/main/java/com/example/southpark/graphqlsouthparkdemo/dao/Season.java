package com.example.southpark.graphqlsouthparkdemo.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Season {

    @Id
    private String id;

    private String name;
    private int yearOfAiringStart;
    private int yearOfAiringEnd;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private List<Episode> episodes;

    public com.example.southpark.graphqlsouthparkdemo.model.Season toModel(){
        com.example.southpark.graphqlsouthparkdemo.model.Season model = toSimpleModel();
        model.setEpisodes(this.episodes.stream().map(episode -> episode.toModel()).collect(Collectors.toList()));
        return model;
    }

    public com.example.southpark.graphqlsouthparkdemo.model.Season toSimpleModel(){
        com.example.southpark.graphqlsouthparkdemo.model.Season model = new com.example.southpark.graphqlsouthparkdemo.model.Season();
        model.setId(this.id);
        model.setYearOfAiringEnd(this.yearOfAiringEnd);
        model.setYearOfAiringStart(this.yearOfAiringStart);
        model.setName(this.name);
        return model;
    }

}

