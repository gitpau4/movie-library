import api from "./api";
import type { Movie } from "../types/Movie";

export async function getMovies(): Promise<Movie[]> {
    const response = await api.get<Movie[]>("/movies");
    return response.data;
}