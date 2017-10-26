package com.example.southpark.graphqlsouthparkdemo.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.southpark.graphqlsouthparkdemo.model.Character;
import com.example.southpark.graphqlsouthparkdemo.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    CharacterService characterService;

    public List<Character> characters() {
        return characterService.getAllCharacters();
    }

    public Character character(String name) {
        return characterService.getCharactersWithName(name);
    }
}
