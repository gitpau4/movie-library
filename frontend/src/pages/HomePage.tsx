import { useEffect, useState } from "react";
import { getMovies } from "../services/movieService";
import type { Movie } from "../types/Movie";
import MovieCard from "../components/MovieCard";

function HomePage() {
    const [movies, setMovies] = useState<Movie[]>([]);

    useEffect(() => {
        getMovies()
            .then((data) => {
                setMovies(data);
            })
            .catch((error) => {
                console.error("Failed to fetch movies:", error);
            });
    }, []);

    return (
        <div>
            <h1>Movie Library</h1>
            
            {movies.map((movie) => (
                <MovieCard key={movie.id} movie={movie} />
            ))}
        </div>
    );
}

export default HomePage;