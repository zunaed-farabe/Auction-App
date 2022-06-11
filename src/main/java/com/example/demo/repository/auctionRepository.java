package com.example.demo.repository;

import com.example.demo.model.auction;
import com.example.demo.model.user;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface auctionRepository extends MongoRepository<auction, String> {



    @Query("{'id': ?0}")
    Optional<auction> findById(String id);


}
