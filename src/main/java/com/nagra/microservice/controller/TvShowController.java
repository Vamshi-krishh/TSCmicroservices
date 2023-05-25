package com.nagra.microservice.controller;

import com.nagra.microservice.repository.Character;
import com.nagra.microservice.TvShow;
import com.nagra.microservice.CharacterRepository;
import com.nagra.microservice.TvShowRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1/shows")
@Api(tags = "TV Shows")
public class TvShowController {
    private static final Logger logger = LoggerFactory.getLogger(TvShowController.class);

    private final TvShowRepository tvShowRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public TvShowController(TvShowRepository tvShowRepository, CharacterRepository characterRepository) {
        this.tvShowRepository = tvShowRepository;
        this.characterRepository = characterRepository;
    }

    @GetMapping
    @ApiOperation("Get all TV shows")
    public List<TvShow> getAllTvShows() {
        logger.info("Fetching all TV shows");
        return tvShowRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a TV show by ID")
    public ResponseEntity<TvShow> getTvShowById(@PathVariable(value = "id") Long tvShowId) {
        logger.info("Fetching TV show with ID: {}", tvShowId);
        Optional<TvShow> optionalTvShow = tvShowRepository.findById(tvShowId);
        if (optionalTvShow.isPresent()) {
            TvShow tvShow = optionalTvShow.get();
            return ResponseEntity.ok().body(tvShow);
        } else {
            logger.error("TV show with ID {} not found", tvShowId);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/characters")
    @ApiOperation("Get characters by TV show ID")
    public ResponseEntity<Set<Character>> getCharactersByTvShowId(@PathVariable(value = "id") Long tvShowId) {
        logger.info("Fetching characters for TV show with ID: {}", tvShowId);
        Set<Character> characters = new HashSet<>(characterRepository.findByTvShowId(tvShowId));
        if (characters.isEmpty()) {
            logger.error("No characters found for TV show with ID: {}", tvShowId);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(characters);
    }

  /*  @PostMapping
    @ApiOperation("Create a new TV show")
    public ResponseEntity<TvShow> createTvShow(@RequestBody @Valid TvShow tvShow) {
        logger.info("Creating a new TV show: {}", tvShow);
        TvShow createdTvShow = tvShowRepository.save(tvShow);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTvShow);
    }*/
  @PostMapping
  public ResponseEntity<TvShow> addTvShow(@RequestBody TvShow tvShow) {
      TvShow savedTvShow = tvShowRepository.save(tvShow);
      return ResponseEntity.status(HttpStatus.CREATED).body(savedTvShow);
  }

    @PostMapping("/{id}/characters")
    @ApiOperation("Add a character to a TV show")
    public ResponseEntity<Character> addCharacterToTvShow(@PathVariable(value = "id") Long tvShowId, @RequestBody @Valid Character character) {
        logger.info("Adding character {} to TV show with ID: {}", character, tvShowId);
        Optional<TvShow> optionalTvShow = tvShowRepository.findById(tvShowId);
        if (optionalTvShow.isPresent()) {
            TvShow tvShow = optionalTvShow.get();
            character.setTvShow(tvShow);
            Character createdCharacter = characterRepository.save(character);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCharacter);
        } else {
            logger.error("TV show with ID {} not found", tvShowId);
            return ResponseEntity.notFound().build();
        }
    }

    // Add other necessary methods and mappings here

}
