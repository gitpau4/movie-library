package com.paula.movielibrary.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.paula.movielibrary.repository.MovieRepository;
import com.paula.movielibrary.model.Movie;
import com.paula.movielibrary.model.TmdbMovie;

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

    public Movie saveMovieFromTmdb(TmdbMovie tmdbMovie) {
        Movie movie = new Movie();

        movie.setTmdbId(tmdbMovie.getId());
        movie.setTitle(tmdbMovie.getTitle());
        movie.setOverview(tmdbMovie.getOverview());
        movie.setReleaseDate(tmdbMovie.getRelease_date());
        movie.setPosterPath(tmdbMovie.getPoster_path());
        movie.setRating(tmdbMovie.getVote_average());

        return movieRepository.save(movie);
    }
}
