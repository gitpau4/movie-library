package com.paula.movielibrary.model;

import java.time.LocalDate;

public class TmdbMovie {
    
    private Long id;
    private String title;
    private String overview;
    private LocalDate release_date;
    private String poster_path;
    private Double vote_average;

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

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Double getVote_average() {
        return vote_average;
    }
    
    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }
}
