package com.example.southpark.graphqlsouthparkdemo.dao.repo;

import com.example.southpark.graphqlsouthparkdemo.dao.Season;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "seasons", path = "seasons")
public interface SeasonRepository extends PagingAndSortingRepository<Season, String> {

    Season findByName(@Param("name") String name);
    Season findById(String id);
}
