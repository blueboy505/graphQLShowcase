package com.example.southpark.graphqlsouthparkdemo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Character {

    private String name;
    private String description;
    private List<Episode> appearsIn = new ArrayList<>();
    private int[] appIn;

    public com.example.southpark.graphqlsouthparkdemo.dao.Character toDAO() {
        com.example.southpark.graphqlsouthparkdemo.dao.Character dao = new com.example.southpark.graphqlsouthparkdemo.dao.Character();
        dao.setDescription(this.description);
        dao.setName(this.name);
        dao.setAppearsIn(appearsIn.stream().map(episode -> episode.getId()).collect(Collectors.joining(",")));
        return dao;
    }

}
