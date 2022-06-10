package com.example.demo.repository;

import com.example.demo.model.item;
import com.example.demo.model.user;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends MongoRepository<user, String> {



        @Query("{'email': ?0}")
        Optional<user> findByEmail(String email);


}
