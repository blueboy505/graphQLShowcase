package com.example.southpark.graphqlsouthparkdemo.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.southpark.graphqlsouthparkdemo.model.Character;
import com.example.southpark.graphqlsouthparkdemo.model.InputCharacter;
import com.example.southpark.graphqlsouthparkdemo.service.CharacterService;
import com.example.southpark.graphqlsouthparkdemo.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    EpisodeService episodeService;

    @Autowired
    CharacterService characterService;

    public Character addNewCharacter(InputCharacter input) {
        Character newCharacter = new Character(input.getName(), input.getDescription());
        for(int episodeNum : input.getAppIn()) {
            com.example.southpark.graphqlsouthparkdemo.dao.Episode episodeDao = episodeService.getDaoById(Integer.toString(episodeNum));
            if(episodeDao != null) {
                newCharacter.getAppearsIn().add(episodeDao.toModel());
            }
        }
        characterService.addNewCharacter(newCharacter);
        return newCharacter;
    }
}
