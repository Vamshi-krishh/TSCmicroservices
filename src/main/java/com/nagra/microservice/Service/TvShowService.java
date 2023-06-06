package com.nagra.microservice.Service;

import com.nagra.microservice.repository.CharacterRepository;
import com.nagra.microservice.repository.TvShowRepository;
import entity.Character;
import entity.TvShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TvShowService {
    private final TvShowRepository tvShowRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public TvShowService(TvShowRepository tvShowRepository, CharacterRepository characterRepository) {
        this.tvShowRepository = tvShowRepository;
        this.characterRepository = characterRepository;
    }

    public List<TvShow> getAllTvShows() {
        return tvShowRepository.findAll();
    }

    public Optional<TvShow> getTvShowById(Long tvShowId) {
        return tvShowRepository.findById(tvShowId);
    }

    public Set<Character> getCharactersByTvShowId(Long tvShowId) {
        return new HashSet<>(characterRepository.findByTvShowId(tvShowId));
    }

    public TvShow addTvShow(TvShow tvShow) {
        return tvShowRepository.save(tvShow);
    }

    public Character addCharacterToTvShow(Long tvShowId, Character character) {
        Optional<TvShow> optionalTvShow = tvShowRepository.findById(tvShowId);
        if (optionalTvShow.isPresent()) {
            TvShow tvShow = optionalTvShow.get();
            character.setTvShow(tvShow);
            return characterRepository.save(character);
        } else {
            return null;
        }
    }

    public Optional<Character> getCharacterById(Long characterId) {
        return null;
    }

    public Character updateCharacter(Character existingCharacter) {
        return existingCharacter;
    }

    public TvShow updateTvShow(TvShow existingTvShow) {
        return existingTvShow;
    }
}
