package com.example.WebAppCinephiles.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Table(name="FilmGenreKey")
public class FilmGenreKey implements Serializable {
    @Column(name="movie_id")
    private long movieId;

    @Column(name="id_genres")
    private long genreId;

    public FilmGenreKey(long movieId, long genreId) {
        this.movieId = movieId;
        this.genreId = genreId;
    }

    public FilmGenreKey() {
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmGenreKey that = (FilmGenreKey) o;
        return movieId == that.movieId && genreId == that.genreId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, genreId);
    }

    @Override
    public String toString() {
        return "FilmGenresKey{" +
                "movieId=" + movieId +
                ", genreId=" + genreId +
                '}';
    }
}
