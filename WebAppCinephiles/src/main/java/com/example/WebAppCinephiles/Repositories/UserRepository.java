package com.example.WebAppCinephiles.Repositories;


import com.example.WebAppCinephiles.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.id != :id")
    List<User> findFriends(long id);
}