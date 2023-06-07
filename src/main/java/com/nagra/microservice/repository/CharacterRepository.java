package com.nagra.microservice.repository;

<<<<<<< HEAD
import com.nagra.microservice.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
=======
import entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

>>>>>>> 1fb768f9b744b845561c4381bdfda96a3c100935
public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByTvShowId(Long tvShowId);
}