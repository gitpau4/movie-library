package com.paula.movielibrary.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.paula.movielibrary.repository.MovieRepository;
import com.paula.movielibrary.model.Movie;

@Service
public class MovieService {
    
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {
        Movie existingMovie = movieRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Movie not found"));
        
        existingMovie.setTmdbId(updatedMovie.getTmdbId());
        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setOverview(updatedMovie.getOverview());
        existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
        existingMovie.setPosterPath(updatedMovie.getPosterPath());

        return movieRepository.save(existingMovie);
    }
}
