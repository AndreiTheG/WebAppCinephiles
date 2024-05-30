package com.example.WebAppCinephiles.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Table(name = "UserFilmKey")
public class UserFilmKey implements Serializable {
    @Column(name = "user_id")
    private long userId;
    @Column(name = "movie_id")
    private long movieId;

    public UserFilmKey(long userId, long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public UserFilmKey() {

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFilmKey that = (UserFilmKey) o;
        return userId == that.userId && movieId == that.movieId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, movieId);
    }
}
