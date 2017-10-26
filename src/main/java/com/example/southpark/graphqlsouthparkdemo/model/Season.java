package com.example.southpark.graphqlsouthparkdemo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Season {

    private String id;
    private String name;

    private int yearOfAiringStart;
    private int yearOfAiringEnd;

    private List<Episode> episodes;

}
