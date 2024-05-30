package com.example.WebAppCinephiles.Repositories;

import com.example.WebAppCinephiles.Models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findByName(String name);
}
