package com.example.WebAppCinephiles.Repositories;

import com.example.WebAppCinephiles.Models.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Long> {
    List<Messages> findByUserIdSender(long userId);
}
