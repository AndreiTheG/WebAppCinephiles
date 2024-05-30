package com.example.WebAppCinephiles.Models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.io.Serializable;

@Entity
@Table(name="genres")
public class Genres implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_genres")
    private long idGenre;
    @NonNull
    private String name;

    public Genres() {
    }

    public Genres(@NonNull String name) {
        this.name = name;
    }

    public void setIdGenre(long idGenre) {
        this.idGenre = idGenre;
    }

    public long getIdGenre() {
        return idGenre;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genres{" +
                "idGenre=" + idGenre +
                ", name='" + name + '\'' +
                '}';
    }
}