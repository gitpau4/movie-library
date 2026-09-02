package com.paula.movielibrary.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paula.movielibrary.model.TmdbMovie;
import com.paula.movielibrary.model.TmdbSearchResponse;
import com.paula.movielibrary.service.TmdbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/tmdb")
public class TmdbController {
    
    private final TmdbService tmdbService;

    public TmdbController(TmdbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @GetMapping("/movies/search")
    public TmdbSearchResponse searchMovie(@RequestParam String query) {
        return tmdbService.searchMovies(query);
    }

    @GetMapping("/movies/{tmdbId}")
    public TmdbMovie getMovie(@PathVariable Long tmdbId) {
        return tmdbService.getMovie(tmdbId);
    }
}
