package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface CountryRepository extends MongoRepository<Country, Long>, QuerydslPredicateExecutor<Country> {
//    @Query("{column:?0}")
//    Optional<Country> findByCode(String code);
}
