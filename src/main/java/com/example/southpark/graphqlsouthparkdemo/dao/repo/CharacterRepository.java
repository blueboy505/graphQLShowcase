package com.example.southpark.graphqlsouthparkdemo.dao.repo;

import com.example.southpark.graphqlsouthparkdemo.dao.Character;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "characters", path = "characters")
public interface CharacterRepository extends PagingAndSortingRepository<Character, String> {


    Character findByName(@Param("name") String name);
}
