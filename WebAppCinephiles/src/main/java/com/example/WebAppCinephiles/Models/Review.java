package com.example.WebAppCinephiles.Models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.io.Serializable;

@Entity
@Table(name="Review")
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idReview;

    @NonNull
    private long idUser;

    @NonNull
    private long idMovie;

    @NonNull
    private double rating;

    public Review(long idReview, long idUser, long idMovie, double rating) {
        this.idReview = idReview;
        this.idUser = idUser;
        this.idMovie = idMovie;
        this.rating = rating;
    }

    public Review() {

    }

    public long getIdReview() {
        return idReview;
    }

    public void setIdReview(long idReview) {
        this.idReview = idReview;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(long idMovie) {
        this.idMovie = idMovie;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "idReview=" + idReview +
                ", idUser=" + idUser +
                ", idMovie=" + idMovie +
                ", rating=" + rating +
                '}';
    }
}