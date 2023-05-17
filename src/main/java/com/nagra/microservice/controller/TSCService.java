package com.nagra.microservice.controller;

import com.nagra.microservice.TvShow;
import com.nagra.microservice.repository.Character;
import com.nagra.microservice.TvShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TSCService {
    private final TvShowRepository showRepository;

    public TSCService( TvShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public List<TvShow> getAllShows() {
        return showRepository.findAll();
    }

    public TvShow getShowById(Long id) {
        return showRepository.findById(id).orElse(null);
    }

    public List<Character> getCharactersByShowId(Long id) {
        // Implement logic to fetch characters for a specific show
        // using showRepository or CharacterRepository
        // Return the list of characters
        return null;
    }
}
