package com.example.WebAppCinephiles.Controller;

import com.example.WebAppCinephiles.Models.Comment;
import com.example.WebAppCinephiles.Models.Movies;
import com.example.WebAppCinephiles.Models.User;
import com.example.WebAppCinephiles.Repositories.CommentRepository;
import com.example.WebAppCinephiles.Repositories.MoviesAndTVSeriesRepository;
import com.example.WebAppCinephiles.Repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private UserRepository userRepository;
    private MoviesAndTVSeriesRepository moviesAndTVSeriesRepository;
    private CommentRepository commentRepository;

    public RestController(UserRepository userRepository,
                          MoviesAndTVSeriesRepository moviesAndTVSeriesRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.moviesAndTVSeriesRepository = moviesAndTVSeriesRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/api/user/:{id}")
    public User getUser(@PathVariable("id") long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @GetMapping("/api/movies")
    public List<Movies> getMovies() {
        return moviesAndTVSeriesRepository.findAll();
    }

    @GetMapping("/api/movie/:{id}")
    public Movies getMovie(@PathVariable("id") long id) {
        return moviesAndTVSeriesRepository.findById(id).orElseThrow();
    }

    @GetMapping("/api/movie/:{id}/comments")
    public List<Comment> getTheComments(@PathVariable("id") long id) {
        List<Comment> listComments = commentRepository.findByIdMovie(id);
        return listComments;
    }


}
