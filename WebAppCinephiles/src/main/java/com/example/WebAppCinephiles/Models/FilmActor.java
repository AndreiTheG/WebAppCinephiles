package com.example.WebAppCinephiles.Models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.io.Serializable;

@Entity
@Table(name="FilmActor")
public class FilmActor implements Serializable {
    @EmbeddedId
    @NonNull
    private FilmActorKey id;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    Movies movies;

    @ManyToOne
    @MapsId("actorId")
    @JoinColumn(name = "actor_id")
    Actor actor;

    public FilmActor(@NonNull FilmActorKey id, Movies movies, Actor actor) {
        this.id = id;
        this.movies = movies;
        this.actor = actor;
    }

    public FilmActor() {

    }

    public FilmActorKey getId() {
        return id;
    }

    public void setId(FilmActorKey id) {
        this.id = id;
    }

    public Movies getMoviesAndTVSeries() {
        return movies;
    }

    public void setMoviesAndTVSeries(Movies moviesAndTVSeries) {
        this.movies = moviesAndTVSeries;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @Override
    public String toString() {
        return "FilmActor{" +
                "id=" + id +
                ", moviesAndTVSeries=" + movies +
                ", actor=" + actor +
                '}';
    }
}
