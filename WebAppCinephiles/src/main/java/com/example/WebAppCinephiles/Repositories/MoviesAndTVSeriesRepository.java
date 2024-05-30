package com.example.WebAppCinephiles.Repositories;

import com.example.WebAppCinephiles.Models.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesAndTVSeriesRepository extends JpaRepository<Movies, Long> {
    List<Movies> findByName(String name);
}
