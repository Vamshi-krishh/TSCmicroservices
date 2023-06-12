package com.nagra.microservice.repository;

import com.nagra.microservice.entity.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {


    Optional<TvShow> findByTitle(String title);


   // Optional<TvShow> getTitle(String title);
}
