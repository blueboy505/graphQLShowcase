package com.example.southpark.graphqlsouthparkdemo.dao.repo;

import com.example.southpark.graphqlsouthparkdemo.dao.Episode;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "episodes", path = "episodes")
public interface EpisodeRepository extends PagingAndSortingRepository<Episode, String> {

    Episode findByName(@Param("name") String name);
    List<Episode> findByAirDate(@Param("airdate") Date airDate);
}
