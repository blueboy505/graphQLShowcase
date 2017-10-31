package com.example.southpark.graphqlsouthparkdemo.dataimporter;

import com.example.southpark.graphqlsouthparkdemo.model.Character;
import com.example.southpark.graphqlsouthparkdemo.model.Episode;
import com.example.southpark.graphqlsouthparkdemo.model.Season;
import com.example.southpark.graphqlsouthparkdemo.service.CharacterService;
import com.example.southpark.graphqlsouthparkdemo.service.EpisodeService;
import com.example.southpark.graphqlsouthparkdemo.service.SeasonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ImportData implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    EpisodeService episodeService;

    @Autowired
    CharacterService characterService;

    @Autowired
    SeasonService seasonService;

    public void loadData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
        mapper.setDateFormat(df);

        List<Season> allSeasons = loadAndStoreAllSeasons(mapper);

        Map<String, Episode> episodeMap = createAllEpisodesMap(allSeasons);

        InputStream is = Character[].class.getResourceAsStream("/static/main_characters.json");
        Character [] mainCharacters = mapper.readValue(is, Character[].class);
        // Main characters appear in all episodes
        for (Character main : mainCharacters) {
            main.setAppearsIn(new ArrayList<>(episodeMap.values()));
            characterService.addNewCharacter(main);
        }

        is = Character[].class.getResourceAsStream("/static/characters.json");
        Character [] supportingCharacters = mapper.readValue(is, Character[].class);
        List<Character> supportingCharactersList = populateCharacters(supportingCharacters, episodeMap);
        for(Character character : supportingCharactersList) {
            characterService.addNewCharacter(character);
        }
    }

    private List<Season> loadAndStoreAllSeasons(ObjectMapper mapper) throws IOException {
        List<Season> allSeasons = new ArrayList<>();
        allSeasons.add(populateSeason(mapper, "/static/s1.json", "1", 1997, 1998));
        allSeasons.add(populateSeason(mapper, "/static/s2.json", "2", 1998, 1999));
        allSeasons.add(populateSeason(mapper, "/static/s3.json", "3", 1999, 2000));
        allSeasons.add(populateSeason(mapper, "/static/s4.json", "4", 2000, 2001));
        allSeasons.add(populateSeason(mapper, "/static/s5.json", "5", 2001, 2002));
        allSeasons.add(populateSeason(mapper, "/static/s6.json", "6", 2002, 2003));
        allSeasons.add(populateSeason(mapper, "/static/s7.json", "7", 2003, 2004));
        allSeasons.add(populateSeason(mapper, "/static/s8.json", "8", 2004, 2005));
        allSeasons.add(populateSeason(mapper, "/static/s9.json", "9", 2005, 2006));
        allSeasons.add(populateSeason(mapper, "/static/s10.json", "10", 2006, 2007));
        allSeasons.add(populateSeason(mapper, "/static/s11.json", "11", 2007, 2008));
        allSeasons.add(populateSeason(mapper, "/static/s12.json", "12", 2008, 2009));
        allSeasons.add(populateSeason(mapper, "/static/s13.json", "13", 2009, 2010));
        allSeasons.add(populateSeason(mapper, "/static/s14.json", "14", 2010, 2011));
        allSeasons.add(populateSeason(mapper, "/static/s15.json", "15", 2011, 2012));
        allSeasons.add(populateSeason(mapper, "/static/s16.json", "16", 2012, 2013));
        allSeasons.add(populateSeason(mapper, "/static/s17.json", "17", 2013, 2014));
        allSeasons.add(populateSeason(mapper, "/static/s18.json", "18", 2014, 2015));
        allSeasons.add(populateSeason(mapper, "/static/s19.json", "19", 2015, 2016));
        allSeasons.add(populateSeason(mapper, "/static/s20.json", "20", 2016, 2017));
        allSeasons.add(populateSeason(mapper, "/static/s21.json", "21", 2017, 2018));
        for(Season season: allSeasons) {
            seasonService.addNewSeason(season);
        }
        return allSeasons;

    }

    private Season populateSeason(ObjectMapper mapper, String url, String id, int airingStart, int airingEnd) throws IOException {
        InputStream is = Episode[].class.getResourceAsStream(url);
        Episode[] s = mapper.readValue(is, Episode[].class);
        Season season = new Season();
        season.setId(id);
        season.setName(id);
        season.setYearOfAiringStart(airingStart);
        season.setYearOfAiringEnd(airingEnd);
        season.setEpisodes(Arrays.asList(s));
        season.getEpisodes().stream().forEach(episode -> episode.setSeason(season));
        return season;
    }

    private Map<String, Episode> createAllEpisodesMap(List<Season> allSeasons) {
        Map<String, Episode> episodeMap = new HashMap<>();
        for(Season cur : allSeasons) {
            for(Episode episode : cur.getEpisodes()) {
                episodeMap.put(episode.getId(), episode);
            }
        }
        return episodeMap;
    }

    private List<Character> populateCharacters(Character[] characters, Map<String, Episode> episodeMap) {
        List<Character> characterList = new ArrayList<>();
        for(Character current : characters){
            for(int episodeId : current.getAppIn()) {
                Episode episode = episodeMap.get(Integer.toString(episodeId));
                current.getAppearsIn().add(episode);
            }
            characterList.add(current);
        }
        return characterList;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }
}
