package com.nagra.microservice.controller;

import com.nagra.microservice.Service.TvShowService;
import com.nagra.microservice.entity.Character;
import com.nagra.microservice.entity.TvShow;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/shows")
@Api(tags = "TV Shows")
public class TvShowController {

    private final TvShowService tvShowService;
    private static final Logger logger = LoggerFactory.getLogger(TvShowController.class);

    @Autowired
    public TvShowController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    @GetMapping
    @ApiOperation("Get all TV shows")
    public List<TvShow> getAllTvShows() {
        logger.debug("Fetching all TV shows");
        return tvShowService.getAllTvShows();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a TV show by ID")
    public ResponseEntity<TvShow> getTvShowById(@PathVariable(value = "id") Long tvShowId) {
        logger.debug("Fetching TV show with ID: {}", tvShowId);
        Optional<TvShow> optionalTvShow = tvShowService.getTvShowById(tvShowId);
        if (optionalTvShow.isPresent()) {
            TvShow tvShow = optionalTvShow.get();
            return ResponseEntity.ok().body(tvShow);
        } else {
            logger.error("TV show with ID {} not found", tvShowId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TV show not found");
        }
    }

    @GetMapping("/{id}/characters")
    @ApiOperation("Get characters by TV show ID")
    public ResponseEntity<Set<Character>> getCharactersByTvShowId(@PathVariable(value = "id") Long tvShowId) {
        logger.debug("Fetching characters for TV show with ID: {}", tvShowId);
        Set<Character> characters = tvShowService.getCharactersByTvShowId(tvShowId);
        if (characters.isEmpty()) {
            logger.error("No characters found for TV show with ID: {}", tvShowId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No characters found for TV show");
        }
        return ResponseEntity.ok().body(characters);
    }

    @PostMapping
    @ApiOperation("Add a new TV show")
    public ResponseEntity<TvShow> addTvShow(@RequestBody @Valid TvShow tvShow) {
        TvShow savedTvShow = tvShowService.addTvShow(tvShow);
        if (savedTvShow != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTvShow);
        } else {
            logger.error("Failed to add TV show");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to add TV show");
        }
    }

    @PostMapping("/{id}/characters")
    @ApiOperation("Add a character to a TV show")
    public ResponseEntity<Character> addCharacterToTvShow(
            @PathVariable(value = "id") Long tvShowId,
            @RequestBody @Valid Character character
    ) {
        logger.debug("Adding character {} to TV show with ID: {}", character, tvShowId);
        Character createdCharacter = tvShowService.addCharacterToTvShow(tvShowId, character);
        if (createdCharacter != null) {return ResponseEntity.status(HttpStatus.CREATED).body(createdCharacter);
        } else {
            logger.error("TV show with ID {} not found", tvShowId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TV show not found");
        }
    }
    @PutMapping("/{id}")
    @ApiOperation("Update a TV show")
    public ResponseEntity<TvShow> updateTvShow(
            @PathVariable(value = "id") Long tvShowId,
            @RequestBody @Valid TvShow updatedTvShow
    ) {
        logger.debug("Updating TV show with ID: {}", tvShowId);
        Optional<TvShow> optionalTvShow = tvShowService.getTvShowById(tvShowId);
        if (optionalTvShow.isPresent()) {
            TvShow existingTvShow = optionalTvShow.get();
            existingTvShow.setTitle(updatedTvShow.getTitle());
            existingTvShow.setSynopsis(updatedTvShow.getSynopsis());
            existingTvShow.setReleaseYear(updatedTvShow.getReleaseYear());
            TvShow updatedShow = tvShowService.updateTvShow(existingTvShow);
            return ResponseEntity.ok().body(updatedShow);
        } else {
            logger.error("TV show with ID {} not found", tvShowId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TV show not found");
        }
    }

    @PutMapping("/{id}/characters/{characterId}")
    @ApiOperation("Update a character in a TV show")
    public ResponseEntity<Character> updateCharacterInTvShow(
            @PathVariable(value = "id") Long tvShowId,
            @PathVariable(value = "characterId") Long characterId,
            @RequestBody @Valid Character updatedCharacter
    ) {
        logger.debug("Updating character with ID {} in TV show with ID: {}", characterId, tvShowId);
        Optional<Character> optionalCharacter = tvShowService.getCharacterById(characterId);
        if (optionalCharacter.isPresent()) {
            Character existingCharacter = optionalCharacter.get();
            existingCharacter.setName(updatedCharacter.getName());
            existingCharacter.setDescription(updatedCharacter.getDescription());
            Character updatedChar = tvShowService.updateCharacter(existingCharacter);
            return ResponseEntity.ok().body(updatedChar);
        } else {
            logger.error("Character with ID {} not found", characterId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found");
        }
    }


}


