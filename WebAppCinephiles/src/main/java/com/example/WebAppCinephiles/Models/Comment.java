package com.example.WebAppCinephiles.Models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="Comments")
public class Comment implements Serializable {
    @Id
    @Column(name="id_comment")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long idComment;
    @NonNull
    @Column(name="id_user")
    private long idUser;
    @NonNull
    @Column(name="id_movie")
    private long idMovie;
    @NonNull
    @Column(name = "username")
    private String username;
    @NonNull
    @Column(name="Content")
    private String content;
    @NonNull
    @Column(name="datePost")
    private Timestamp datePost;

    public Comment(long idUser, long idMovie,@NonNull String username, @NonNull String content, @NonNull Timestamp datePost) {
        this.idUser = idUser;
        this.idMovie = idMovie;
        this.username = username;
        this.content = content;
        this.datePost = datePost;
    }

    public Comment() {

    }

    public long getIdComment() {
        return idComment;
    }

    public void setIdComment(long idComment) {
        this.idComment = idComment;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(long idMovie) {
        this.idMovie = idMovie;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    @NonNull
    public Timestamp getDatePost() {
        return datePost;
    }

    public void setDatePost(@NonNull Timestamp datePost) {
        this.datePost = datePost;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idComment=" + idComment +
                ", idUser=" + idUser +
                ", idMovie=" + idMovie +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", datePost=" + datePost +
                '}';
    }
}
