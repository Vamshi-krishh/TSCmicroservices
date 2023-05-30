package com.nagra.microservice;

import javax.persistence.*;

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

    // constructors, getters, and setters

    public TvShow() {}

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
        return (synopsis != null) ? synopsis : "";
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getReleaseYear() {
        return (releaseYear != null) ? releaseYear : 0;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
}
