import type { Movie } from "../types/Movie";

interface MovieCardProps {
    movie: Movie;
}

function MovieCard({ movie }: MovieCardProps) {
    return (
        <div className="w-full">
            {movie.posterPath && (
                <img
                    src={`https://image.tmdb.org/t/p/w500${movie.posterPath}`}
                    alt={movie.title}
                    className="aspect-2/3 w-full rounded-lg object-cover"
                />
            )}

            <h2 className="mt-2 text-lg font-semibold">
                {movie.title}
            </h2>
        </div>
    );
}

export default MovieCard;