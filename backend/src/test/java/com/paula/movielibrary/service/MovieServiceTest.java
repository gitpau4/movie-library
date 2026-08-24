package com.paula.movielibrary.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.paula.movielibrary.model.Movie;
import com.paula.movielibrary.repository.MovieRepository;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    
    @Mock
    private MovieRepository movieRepository;

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieService = new MovieService(movieRepository);
    }

    @Test
    void getAllMoviesShouldReturnAllMovies() {

        Movie movie1 = new Movie();
        movie1.setTitle("Coraline");

        Movie movie2 = new Movie();
        movie2.setTitle("Hunger Games");

        List<Movie> movies = List.of(movie1, movie2);

        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = movieService.getAllMovies();

        assertEquals(2, result.size());
        assertEquals("Coraline", result.get(0).getTitle());
        assertEquals("Hunger Games", result.get(1).getTitle());

        verify(movieRepository).findAll();
    }

    @Test
    void saveMovieShouldReturnSavedMovie() {

        Movie movie = new Movie();
        movie.setTitle("Coraline");

        when(movieRepository.save(movie)).thenReturn(movie);

        Movie result = movieService.saveMovie(movie);

        assertEquals("Coraline", result.getTitle());

        verify(movieRepository).save(movie);
    }

    @Test
    void getMovieByIdShouldReturnMovie() {

        Movie movie = new Movie();
        movie.setTitle("Coraline");

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        Optional<Movie> result = movieService.getMovieById(1L);

        assertTrue(result.isPresent());
        assertEquals("Coraline", result.get().getTitle());

        verify(movieRepository).findById(1L);
    }

    @Test
    void getMovieByIdShouldReturnEmptyWhenMovieNotExist() {

        when(movieRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Movie> result = movieService.getMovieById(999L);

        assertTrue(result.isEmpty());

        verify(movieRepository).findById(999L);
    }

    @Test
    void deleteMovieShouldDeleteMovie() {

        movieService.deleteMovie(1L);

        // vi kollar endast om movieService faktiskt anropade deleteById
        verify(movieRepository).deleteById(1L);
    }

    @Test
    void updateMovieShouldUpdateAndSaveMovie() {

        Movie existingMovie = new Movie();
        existingMovie.setTitle("Old title");

        Movie updatedMovie = new Movie();
        updatedMovie.setTitle("New title");
        updatedMovie.setOverview("New overview");

        when(movieRepository.findById(1L)).thenReturn(Optional.of(existingMovie));
        when(movieRepository.save(existingMovie)).thenReturn(existingMovie);

        Movie result = movieService.updateMovie(1L, updatedMovie);

        assertEquals("New title", result.getTitle());
        assertEquals("New overview", result.getOverview());

        verify(movieRepository).findById(1L);
        verify(movieRepository).save(existingMovie);
    }

    @Test
    void updateMovieShouldThrowExceptionWhenMovieNotExist() {

        Movie updatedMovie = new Movie();
        updatedMovie.setTitle("New title");

        when(movieRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> movieService.updateMovie(999L, updatedMovie));

        verify(movieRepository).findById(999L);
        verify(movieRepository, never()).save(updatedMovie);
    }
}
