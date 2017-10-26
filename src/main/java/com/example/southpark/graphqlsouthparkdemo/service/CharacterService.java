package com.example.southpark.graphqlsouthparkdemo.service;

import com.example.southpark.graphqlsouthparkdemo.dao.repo.CharacterRepository;
import com.example.southpark.graphqlsouthparkdemo.dao.repo.EpisodeRepository;
import com.example.southpark.graphqlsouthparkdemo.model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    public void addNewCharacter(Character character) {
        characterRepository.save(character.toDAO());
    }

    public List<Character> getAllCharacters() {
        Collection<com.example.southpark.graphqlsouthparkdemo.dao.Character> characters;
        characters = Utility.makeCollection(characterRepository.findAll());
        return characters.stream().map(character -> toModel(character)).collect(Collectors.toList());
    }

    public Character toModel(com.example.southpark.graphqlsouthparkdemo.dao.Character daoCharacter) {
        Character model = new Character();
        model.setDescription(daoCharacter.getDescription());
        model.setName(daoCharacter.getName());
        if(!StringUtils.isEmpty(daoCharacter.getAppearsIn())) {
            model.setAppearsIn((Arrays.stream(daoCharacter.getAppearsIn().split(",")).map(s -> episodeRepository.findOne(s).toModel())).collect(Collectors.toList()));
        }
        return model;
    }
}
