package com.nagra.microservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tv_shows")
public class TvShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "release_year")
    private Integer releaseYear;

    @JsonIgnore
    @OneToMany(mappedBy = "tvShow", cascade = CascadeType.ALL)
    private List<Character> characters;

    // constructors, getters, and setters

    public TvShow() {
    }

    public TvShow(Long id, String title, String synopsis, Integer releaseYear) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.releaseYear = releaseYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public Object getName() {
        return null;
    }

    public Object getDescription() {
        return null;
    }

    public Object getGenre() {
        return null;
    }

    public Object getRating() {
        return null;
    }

    public void setName(Object name) {
    }

    public void setDescription(Object description) {
    }

    public void setGenre(Object genre) {
    }

    public void setRating(Object rating) {
    }
}
