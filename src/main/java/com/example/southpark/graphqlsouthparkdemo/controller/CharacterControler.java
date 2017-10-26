package com.example.southpark.graphqlsouthparkdemo.controller;

import com.example.southpark.graphqlsouthparkdemo.model.Character;
import com.example.southpark.graphqlsouthparkdemo.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/character")
public class CharacterControler {

    @Autowired
    CharacterService characterService;

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Character> getAllCharacters(){
        return characterService.getAllCharacters();
    }

}
