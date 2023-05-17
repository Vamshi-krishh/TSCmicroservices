package com.nagra.microservice;

import com.nagra.microservice.repository.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByTvShowId(Long tvShowId);
}