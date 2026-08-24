package com.paula.movielibrary.repository;

import com.paula.movielibrary.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    
}
