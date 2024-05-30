package com.example.WebAppCinephiles.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Table(name="FilmActorKey")
public class FilmActorKey implements Serializable {
    @Column(name = "movie_id")
    private long movieId;

    @Column(name = "actor_id")
    private long actorId;



    public FilmActorKey(long movieId, long actorId) {
        this.movieId = movieId;
        this.actorId = actorId;
    }


    public FilmActorKey() {

    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getActorId() {
        return actorId;
    }

    public void setActorId(long actorId) {
        this.actorId = actorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmActorKey that = (FilmActorKey) o;
        return movieId == that.movieId && actorId == that.actorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, actorId);
    }
}
