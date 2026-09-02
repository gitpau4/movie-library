package com.paula.movielibrary.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paula.movielibrary.service.MovieService;
import com.paula.movielibrary.model.Movie;
import com.paula.movielibrary.model.TmdbMovie;
import com.paula.movielibrary.service.TmdbService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/movies")
public class MovieController {
    
    private final MovieService movieService;
    private final TmdbService tmdbService;

    public MovieController(MovieService movieService, TmdbService tmdbService) {
        this.movieService = movieService;
        this.tmdbService = tmdbService;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
            .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    @PostMapping("/from-tmdb/{tmdbId}")
    public Movie saveMovieFromTmdb(@PathVariable Long tmdbId) {
        TmdbMovie tmdbMovie = tmdbService.getMovie(tmdbId);
        return movieService.saveMovieFromTmdb(tmdbMovie);
    }
}
