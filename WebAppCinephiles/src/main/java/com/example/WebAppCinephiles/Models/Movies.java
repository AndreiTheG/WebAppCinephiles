package com.example.WebAppCinephiles.Models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.io.Serializable;

@Entity
@Table(name = "MoviesAndTVSeries")
public class Movies implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    public long id;
    @NonNull
    @Column(name = "Name")
    public String name;
    @NonNull
    @Column(name="Genres")
    public String genres;
    @NonNull
    @Column(name = "YearRelease")
    public int yearReleased;
    @NonNull
    @Column(name = "Rating")
    public double rating;
    @NonNull
    @Column(name = "Description")
    public String description;
    @NonNull
    @Column(name = "IsMovieOrTVSeries")
    public String movieOrTVSeries;
    @NonNull
    @Column(name = "Director")
    public String directorName;
    @NonNull
    @Column(name = "Writer")
    public String writer;
    @NonNull
    @Column(name="ImageCover")
    public String imageCover;
    @NonNull
    @Column(name="Nr_Total_Reviews")
    public int nrReviews;

    public Movies() {

    }

    public Movies(long id, @NonNull String name, @NonNull String genres,  @NonNull int yearReleased, @NonNull double rating,
                  @NonNull String description, @NonNull String movieOrTVSeries, @NonNull String directorName,
                  @NonNull String writer, @NonNull String imageCover, @NonNull int nrReviews) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.yearReleased = yearReleased;
        this.rating = rating;
        this.description = description;
        this.movieOrTVSeries = movieOrTVSeries;
        this.directorName = directorName;
        this.writer = writer;
        this.imageCover = imageCover;
        this.nrReviews = nrReviews;
//        this.actorsPlayed = actorsPlayed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getGenres() {
        return genres;
    }

    public void setGenres(@NonNull String genres) {
        this.genres = genres;
    }

    @NonNull
    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(@NonNull int yearReleased) {
        this.yearReleased = yearReleased;
    }

    @NonNull
    public double getRating() {
        return rating;
    }

    public void setRating(@NonNull double rating) {
        this.rating = rating;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public String getMovieOrTVSeries() {
        return movieOrTVSeries;
    }

    public void setMovieOrTVSeries(@NonNull String movieOrTVSeries) {
        this.movieOrTVSeries = movieOrTVSeries;
    }

    @NonNull
    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(@NonNull String directorName) {
        this.directorName = directorName;
    }

    @NonNull
    public String getWriter() {
        return writer;
    }

    public void setWriter(@NonNull String writer) {
        this.writer = writer;
    }

    @NonNull
    public String getImageCover() {
        return imageCover;
    }

    public void setImageCover(@NonNull String imageCover) {
        this.imageCover = imageCover;
    }

    public int getNrReviews() {
        return nrReviews;
    }

    public void setNrReviews(int nrReviews) {
        this.nrReviews = nrReviews;
    }

    //    public Set<FilmActor> getFilmActor() {
//        return filmActor;
//    }
//
//    public void setFilmActor(Set<FilmActor> filmActor) {
//        this.filmActor = filmActor;
//    }

    //    public Set<Actor> getActorsPlayed() {
//        return actorsPlayed;
//    }
//
//    public void setActorsPlayed(Set<Actor> actorsPlayed) {
//        this.actorsPlayed = actorsPlayed;
//    }

//    @Override
//    public String toString() {
//        return "MoviesAndTVSeries{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", genres='" + genres + '\'' +
//                ", yearReleased=" + yearReleased +
//                ", description='" + description + '\'' +
//                ", movieOrTVSeries='" + movieOrTVSeries + '\'' +
//                ", directorName='" + directorName + '\'' +
//                ", writer='" + writer + '\'' +
//                ", imageCover='" + imageCover + '\'' +
//                ", actorsPlayed=" + actorsPlayed +
//                '}';
//    }

//    @Override
//    public String toString() {
//        return "Movies{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", genres='" + genres + '\'' +
//                ", yearReleased=" + yearReleased +
//                ", rating=" + rating +
//                ", description='" + description + '\'' +
//                ", movieOrTVSeries='" + movieOrTVSeries + '\'' +
//                ", directorName='" + directorName + '\'' +
//                ", writer='" + writer + '\'' +
//                ", imageCover='" + imageCover + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genres='" + genres + '\'' +
                ", yearReleased=" + yearReleased +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                ", movieOrTVSeries='" + movieOrTVSeries + '\'' +
                ", directorName='" + directorName + '\'' +
                ", writer='" + writer + '\'' +
                ", imageCover='" + imageCover + '\'' +
                ", nrReviews=" + nrReviews +
                '}';
    }


//        @Override
//    public String toString() {
//        return "MoviesAndTVSeries{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", genres='" + genres + '\'' +
//                ", yearReleased=" + yearReleased +
//                ", description='" + description + '\'' +
//                ", movieOrTVSeries='" + movieOrTVSeries + '\'' +
//                ", directorName='" + directorName + '\'' +
//                ", writer='" + writer + '\'' +
//                ", imageCover='" + imageCover + '\'' +
//                '}';
//    }
}