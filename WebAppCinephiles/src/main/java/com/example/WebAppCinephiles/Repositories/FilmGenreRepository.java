package com.example.WebAppCinephiles.Repositories;

import com.example.WebAppCinephiles.Models.FilmGenre;
import com.example.WebAppCinephiles.Models.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmGenreRepository extends JpaRepository<FilmGenre, Long> {
    public List<FilmGenre> findByMovies(Movies movies);
}
