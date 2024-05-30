package com.example.WebAppCinephiles.Repositories;


import com.example.WebAppCinephiles.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByIdMovie(long idMovie);

    @Query("select c, u.username from Comment c, User u where c in :comments and c.idUser = u.id order by c.datePost desc")
    List<Comment> listComments(List<Comment> comments);
}
