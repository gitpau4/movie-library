import { useEffect, useState } from "react";
import { getMovies } from "../services/movieService";
import type { Movie } from "../types/Movie";
import MovieCard from "../components/MovieCard";
import "./HomePage.css";

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
            <h1 className="text-4xl font-bold text-blue-600">
                Movie Library
            </h1>
            
            <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-4 lg:grid-cols-6 gap-6">
                {movies.map((movie) => (
                    <MovieCard key={movie.id} movie={movie} />
                ))}
            </div>
        </div>
    );
}

export default HomePage;