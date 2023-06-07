package com.nagra.microservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nagra.microservice.entity.TvShow;

import javax.persistence.*;

@Entity
@Table(name = "tv_characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "tv_show_id")
    @JsonIgnore
    private TvShow tvShow;

    // constructors, getters, and setters

    public Character() {}

    public Character(Long id, String name, String description, TvShow tvShow) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tvShow = tvShow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TvShow getTvShow() {
        return tvShow;
    }

    public void setTvShow(TvShow tvShow) {
        this.tvShow = tvShow;
    }
}
