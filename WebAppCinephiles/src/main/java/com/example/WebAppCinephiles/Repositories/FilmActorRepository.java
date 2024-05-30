package com.example.WebAppCinephiles.Repositories;


import com.example.WebAppCinephiles.Models.FilmActor;
import com.example.WebAppCinephiles.Models.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, Long> {
    List<FilmActor> findByMovies(Movies moviesAndTVSeries);
}
