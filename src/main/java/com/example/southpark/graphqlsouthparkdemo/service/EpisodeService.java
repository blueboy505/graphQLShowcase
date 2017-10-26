package com.example.southpark.graphqlsouthparkdemo.service;

import com.example.southpark.graphqlsouthparkdemo.dao.Season;
import com.example.southpark.graphqlsouthparkdemo.dao.repo.EpisodeRepository;
import com.example.southpark.graphqlsouthparkdemo.model.Episode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EpisodeService {

    @Autowired
    EpisodeRepository repository;

    public void addNewEpisode(Episode episode) {
        repository.save(episode.toDAO());
    }

    public List<Episode> getAllEpisodes() {
        Collection<com.example.southpark.graphqlsouthparkdemo.dao.Episode> episodes;
        episodes = Utility.makeCollection(repository.findAll());
        return episodes.stream().map(episode -> episode.toModel()).collect(Collectors.toList());
    }

    public com.example.southpark.graphqlsouthparkdemo.dao.Episode getDaoById(String id) {
        return repository.findOne(id);
    }
}
