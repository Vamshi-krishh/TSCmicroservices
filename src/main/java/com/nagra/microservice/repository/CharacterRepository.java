package com.nagra.microservice.repository;

import com.nagra.microservice.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByTvShowId(Long tvShowId);
}