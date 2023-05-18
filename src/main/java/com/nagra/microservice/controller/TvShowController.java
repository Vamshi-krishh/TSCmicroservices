package com.nagra.microservice.controller;

import com.nagra.microservice.repository.Character;
import com.nagra.microservice.TvShow;
import com.nagra.microservice.CharacterRepository;
import com.nagra.microservice.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/shows")
public class TvShowController {

    private final TvShowRepository tvShowRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public TvShowController(TvShowRepository tvShowRepository, CharacterRepository characterRepository) {
        this.tvShowRepository = tvShowRepository;
        this.characterRepository = characterRepository;
    }

    @GetMapping
    public List<TvShow> getAllTvShows() {
        return tvShowRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvShow> getTvShowById(@PathVariable(value = "id") Long tvShowId) {
        Optional<TvShow> optionalTvShow = tvShowRepository.findById(tvShowId);
        if (optionalTvShow.isPresent()) {
            TvShow tvShow = optionalTvShow.get();
            return ResponseEntity.ok().body(tvShow);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/characters")
    public ResponseEntity<Set<Character>> getCharactersByTvShowId(@PathVariable(value = "id") Long tvShowId) {
        Set<Character> characters = new HashSet<>(characterRepository.findByTvShowId(tvShowId));
        if (characters.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(characters);
    }

    // Add methods for inserting new TV shows and characters here

}
