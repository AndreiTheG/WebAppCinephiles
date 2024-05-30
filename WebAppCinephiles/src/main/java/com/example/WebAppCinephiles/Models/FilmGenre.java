package com.example.WebAppCinephiles.Models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.io.Serializable;

@Entity
@Table(name="FilmGenre")
public class FilmGenre implements Serializable {

    @EmbeddedId
    @NonNull
    private FilmGenreKey id;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    Movies movies;

    @ManyToOne
    @MapsId("genreId")
    @JoinColumn(name = "genres_id")
    Genres genre;

    public FilmGenre(@NonNull FilmGenreKey id, Movies movies, Genres genre) {
        this.id = id;
        this.movies = movies;
        this.genre = genre;
    }

    public FilmGenre() {
    }

    public void setId(FilmGenreKey id) {
        this.id = id;
    }

    public FilmGenreKey getId() {
        return id;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    public Genres getGenre() {
        return genre;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "FilmGenre{" +
                "id=" + id +
                ", movies=" + movies +
                ", genre=" + genre +
                '}';
    }
}
