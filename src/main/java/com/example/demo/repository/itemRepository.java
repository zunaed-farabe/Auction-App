package com.example.demo.repository;

import com.example.demo.model.item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface itemRepository extends MongoRepository<item, String> {

    @Query("{'name': ?0}")
    Optional<item> findByName(String name);
    @Query("{'ownerId': ?0}")
    Optional<item> findByOwnerId(String ownerId);

}
