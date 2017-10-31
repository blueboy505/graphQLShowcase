package com.example.southpark.graphqlsouthparkdemo.model;

import com.example.southpark.graphqlsouthparkdemo.service.EpisodeService;
import com.example.southpark.graphqlsouthparkdemo.service.SeasonService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Getter
@Setter
public class Episode {

    private String id;
    private String name;
    private Date airDate;
    private Season season;
    private String description;

    public com.example.southpark.graphqlsouthparkdemo.dao.Episode toDAO() {
        com.example.southpark.graphqlsouthparkdemo.dao.Episode dao = dao = new com.example.southpark.graphqlsouthparkdemo.dao.Episode();
        dao.setId(this.id);
        dao.setAirDate(this.getAirDate());
        dao.setName(this.getName());
        dao.setDescription(this.getDescription());
        return dao;
    }
}
