package com.nagra.microservice.Service;

import com.nagra.microservice.entity.Character;
import com.nagra.microservice.repository.CharacterRepository;
import com.nagra.microservice.entity.TvShow;
import com.nagra.microservice.repository.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Validated
public class TvShowService {

    @Autowired
    private TvShowRepository tvShowRepository;

    @Autowired
    private CharacterRepository characterRepository;

    public List<TvShow> getAllTvShows() {
        return tvShowRepository.findAll();
    }

    public Optional<TvShow> getTvShowById(Long tvShowId) {
        return tvShowRepository.findById(tvShowId);
    }

    public Set<Character> getCharactersByTvShowId(Long tvShowId) {
        return new HashSet<>(characterRepository.findByTvShowId(tvShowId));
    }

    public TvShow addTvShow(@NotNull @Valid TvShow tvShow) {
        validateUniqueTvShowName(tvShow.getTitle());
        validateReleaseYearFormat(tvShow.getReleaseYear());
        return tvShowRepository.save(tvShow);
    }

    public Character addCharacterToTvShow(Long tvShowId, @NotNull @Valid Character character) {
        Optional<TvShow> optionalTvShow = tvShowRepository.findById(tvShowId);
        if (optionalTvShow.isPresent()) {
            TvShow tvShow = optionalTvShow.get();
            validateUniqueCharacterName(tvShowId, character.getName());
            character.setTvShow(tvShow);
            return characterRepository.save(character);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TV show not found.");
        }
    }

    public Optional<Character> getCharacterById(Long characterId) {
        return characterRepository.findById(characterId);
    }

    public Character updateCharacter(@NotNull @Valid Character existingCharacter) {
        return characterRepository.save(existingCharacter);
    }

    public TvShow updateTvShow(@NotNull @Valid TvShow existingTvShow) {
        return tvShowRepository.save(existingTvShow);
    }

    private void validateUniqueTvShowName(String title) {
        Optional<TvShow> existingTvShow = tvShowRepository.findByTitle(title);
        if (existingTvShow.isPresent()) {
            throw new CustomException("TV show with the same name already exists.", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateUniqueCharacterName(Long tvShowId, String name) {
        Optional<Character> existingCharacter = characterRepository.findByTvShowIdAndName(tvShowId, name);
        if (existingCharacter.isPresent()) {
            throw new CustomException("Character with the same name already exists in the TV show.", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateReleaseYearFormat(LocalDateTime releaseYear) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            formatter.format(releaseYear);
        }
        catch (DateTimeException e) {
            throw new CustomException("Invalid release year format. Please provide a valid date and time.", HttpStatus.BAD_REQUEST);
        }
    }

    // CustomException class
    private static class CustomException extends ResponseStatusException {
        public CustomException(String message, HttpStatus status) {
            super(status, message);
        }

        public CustomException(String message, HttpStatus status, Throwable cause) {
            super(status, message, cause);
        }
    }
}
