package com.paula.movielibrary.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.paula.movielibrary.model.TmdbMovie;
import com.paula.movielibrary.model.TmdbSearchResponse;

@Service
public class TmdbService {

    private final RestClient restClient;

    @Value("${tmdb.api.key}")
    private String apiKey;

    public TmdbService(@Value("${tmdb.base.url}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    // söker efter filmer utifrån namn
    public TmdbSearchResponse searchMovies(String query) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/search/movie")
                    .queryParam("api_key", apiKey)
                    .queryParam("query", query)
                    .build())
                .retrieve()
                .body(TmdbSearchResponse.class);
    }

    // hämtar film utifrån dess id
    public TmdbMovie getMovie(Long tmdbId) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/movie/{id}")
                    .queryParam("api_key", apiKey)
                    .build(tmdbId))
                .retrieve()
                .body(TmdbMovie.class);
    }
}
