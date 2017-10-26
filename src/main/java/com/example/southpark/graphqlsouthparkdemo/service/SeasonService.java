package com.example.southpark.graphqlsouthparkdemo.service;

import com.example.southpark.graphqlsouthparkdemo.dao.repo.EpisodeRepository;
import com.example.southpark.graphqlsouthparkdemo.dao.repo.SeasonRepository;
import com.example.southpark.graphqlsouthparkdemo.model.Episode;
import com.example.southpark.graphqlsouthparkdemo.model.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeasonService {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    public void addNewSeason(Season season) {
        seasonRepository.save(toDAO(season));
    }

    public com.example.southpark.graphqlsouthparkdemo.dao.Season toDAO(Season season) {
        com.example.southpark.graphqlsouthparkdemo.dao.Season dao = new com.example.southpark.graphqlsouthparkdemo.dao.Season();
        dao.setId(season.getId());
        dao.setName(season.getName());
        dao.setYearOfAiringEnd(season.getYearOfAiringEnd());
        dao.setYearOfAiringStart(season.getYearOfAiringStart());
        dao.setEpisodes(season.getEpisodes().stream().map(episode -> {
            com.example.southpark.graphqlsouthparkdemo.dao.Episode episodeDao = episodeRepository.findOne(episode.getId());
            if(episodeDao != null) {
                return episodeDao;
            } else {
                return episode.toDAO();
            }
        }).collect(Collectors.toList()));
        dao.getEpisodes().stream().forEach(episode -> episode.setSeason(dao));
        return dao;
    }

    public List<Season> getAllSeasons() {
        Collection<com.example.southpark.graphqlsouthparkdemo.dao.Season> seasons;
        seasons = Utility.makeCollection(seasonRepository.findAll());
        return seasons.stream().map(season -> season.toModel()).collect(Collectors.toList());
    }

    public com.example.southpark.graphqlsouthparkdemo.dao.Season getSeasonDao(String id) {
        return seasonRepository.findById(id);
    }

}
