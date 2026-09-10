import type { Movie } from "../types/Movie";

interface MovieCardProps {
    movie: Movie;
}

function MovieCard({ movie }: MovieCardProps) {
    return (
        <div>
            <h2>{movie.title}</h2>
        </div>
    );
}

export default MovieCard;