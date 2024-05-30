package com.example.WebAppCinephiles.Repositories;

import com.example.WebAppCinephiles.Models.User;
import com.example.WebAppCinephiles.Models.UserFilm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFilmRepository extends JpaRepository<UserFilm, Long> {
    List<UserFilm> findByUser(User user);
}
