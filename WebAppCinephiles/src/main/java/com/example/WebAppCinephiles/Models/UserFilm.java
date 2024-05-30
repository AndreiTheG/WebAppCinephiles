package com.example.WebAppCinephiles.Models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="UserFilm")
public class UserFilm implements Serializable {
    @EmbeddedId
    private UserFilmKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    Movies movies;

    public UserFilm(UserFilmKey id, User user, Movies movies) {
        this.id = id;
        this.user = user;
        this.movies = movies;
    }

    public UserFilm() {
    }

    public UserFilmKey getId() {
        return id;
    }

    public void setId(UserFilmKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movies getMovies() {
        return movies;
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "UserFilm{" +
                "id=" + id +
                ", user=" + user +
                ", movies=" + movies +
                '}';
    }
}
