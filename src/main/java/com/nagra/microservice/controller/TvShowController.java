package com.nagra.microservice.controller;

import com.nagra.microservice.Service.TvShowService;
<<<<<<< HEAD
import com.nagra.microservice.entity.Character;
import com.nagra.microservice.entity.TvShow;
=======
import entity.Character;
import entity.TvShow;
>>>>>>> 1fb768f9b744b845561c4381bdfda96a3c100935
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/shows")
@Api(tags = "TV Shows")
public class TvShowController {
<<<<<<< HEAD


    @Autowired
    private TvShowService tvShowService;
    private static final Logger logger = LoggerFactory.getLogger(TvShowController.class);

=======
    private static final Logger logger = LoggerFactory.getLogger(TvShowController.class);

    private final TvShowService tvShowService;

    @Autowired
>>>>>>> 1fb768f9b744b845561c4381bdfda96a3c100935
    public TvShowController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @GetMapping
    @ApiOperation("Get all TV shows")
    public List<TvShow> getAllTvShows() {
        logger.info("Fetching all TV shows");
        return tvShowService.getAllTvShows();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a TV show by ID")
    public ResponseEntity<TvShow> getTvShowById(@PathVariable(value = "id") Long tvShowId) {
        logger.info("Fetching TV show with ID: {}", tvShowId);
        Optional<TvShow> optionalTvShow = tvShowService.getTvShowById(tvShowId);
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
        Set<Character> characters = tvShowService.getCharactersByTvShowId(tvShowId);
        if (characters.isEmpty()) {
            logger.error("No characters found for TV show with ID: {}", tvShowId);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(characters);
    }

    @PostMapping
    @ApiOperation("Add a new TV show")
    public ResponseEntity<TvShow> addTvShow(@RequestBody TvShow tvShow) {
        TvShow savedTvShow = tvShowService.addTvShow(tvShow);
        if (savedTvShow != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTvShow);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/characters")
    @ApiOperation("Add a character to a TV show")
    public ResponseEntity<Character> addCharacterToTvShow(@PathVariable(value = "id") Long tvShowId, @RequestBody @Valid Character character) {
        logger.info("Adding character {} to TV show with ID: {}", character, tvShowId);
        Character createdCharacter = tvShowService.addCharacterToTvShow(tvShowId, character);
        if (createdCharacter != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCharacter);
        } else {
            logger.error("TV show with ID {} not found", tvShowId);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @ApiOperation("Update a TV show")
    public ResponseEntity<TvShow> updateTvShow(@PathVariable(value = "id") Long tvShowId, @RequestBody @Valid TvShow updatedTvShow) {
        logger.info("Updating TV show with ID: {}", tvShowId);
        Optional<TvShow> optionalTvShow = tvShowService.getTvShowById(tvShowId);
        if (optionalTvShow.isPresent()) {
            TvShow existingTvShow = optionalTvShow.get();
            existingTvShow.setName(updatedTvShow.getName());
            existingTvShow.setDescription(updatedTvShow.getDescription());
            existingTvShow.setGenre(updatedTvShow.getGenre());
            existingTvShow.setRating(updatedTvShow.getRating());
            TvShow updatedShow = tvShowService.updateTvShow(existingTvShow);
            return ResponseEntity.ok().body(updatedShow);
        } else {
            logger.error("TV show with ID {} not found", tvShowId);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/characters/{characterId}")
    @ApiOperation("Update a character in a TV show")
    public ResponseEntity<Character> updateCharacterInTvShow(@PathVariable(value = "id") Long tvShowId, @PathVariable(value = "characterId") Long characterId, @RequestBody @Valid Character updatedCharacter) {
        logger.info("Updating character with ID {} in TV show with ID: {}", characterId, tvShowId);
        Optional<Character> optionalCharacter = tvShowService.getCharacterById(characterId);
        if (optionalCharacter.isPresent()) {
            Character existingCharacter = optionalCharacter.get();
            existingCharacter.setName(updatedCharacter.getName());
            existingCharacter.setDescription(updatedCharacter.getDescription());
            Character updatedChar = tvShowService.updateCharacter(existingCharacter);
            return ResponseEntity.ok().body(updatedChar);
        } else {
            logger.error("Character with ID {} not found", characterId);
            return ResponseEntity.notFound().build();
        }
    }

<<<<<<< HEAD
=======
    // Add other necessary methods and mappings here
>>>>>>> 1fb768f9b744b845561c4381bdfda96a3c100935
}
