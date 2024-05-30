package com.example.WebAppCinephiles.Controller;

import com.example.WebAppCinephiles.Models.Movies;
import com.example.WebAppCinephiles.Models.User;
import com.example.WebAppCinephiles.Models.UserFilm;
import com.example.WebAppCinephiles.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WatchlistController {
    public MoviesAndTVSeriesRepository moviesAndTVSeriesRepository;
    public ActorRepository actorRepository;
    public UserRepository userRepository;
    public long currentUserId;
    public FilmActorRepository filmActorRepository;
    public ReviewRepository reviewRepository;
    public CommentRepository commentRepository;
    public UserFilmRepository userFilmRepository;
    public MessageRepository messageRepository;
    public SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WatchlistController(MoviesAndTVSeriesRepository moviesAndTVSeriesRepository, UserRepository userRepository,
                          ActorRepository actorRepository, FilmActorRepository filmActorRepository,
                          ReviewRepository reviewRepository, CommentRepository commentRepository,
                          UserFilmRepository userFilmRepository, MessageRepository messageRepository,
                          SimpMessagingTemplate messagingTemplate) {
        this.moviesAndTVSeriesRepository = moviesAndTVSeriesRepository;
        this.userRepository = userRepository;
        this.actorRepository = actorRepository;
        this.filmActorRepository = filmActorRepository;
        this.reviewRepository = reviewRepository;
        this.commentRepository = commentRepository;
        this.userFilmRepository = userFilmRepository;
        this.messageRepository = messageRepository;
        this.messagingTemplate = messagingTemplate;
    }
    @PostMapping("/{idUser}/watchlist/{idMovie}")
    public String deleteFromList(@PathVariable("idUser") long idUser, @PathVariable("idMovie") long movieId) {
        if (idUser == 0) {
            return "redirect:/login/";
        }
        Movies movies = moviesAndTVSeriesRepository.findById(movieId).orElseThrow();
        List<UserFilm> userFilms = userFilmRepository
                .findByUser(userRepository.findById(idUser).orElseThrow());
        User user = userRepository.findById(idUser).orElseThrow();
        UserFilm userFilm = new UserFilm();
        boolean isPressed = false;
        for (UserFilm userFilm1 : userFilms) {
            if (userFilm1.getMovies().equals(movies)) {
                userFilm.setId(userFilm1.getId());
                userFilm.setUser(user);
                userFilm.setMovies(movies);
                isPressed = true;
                break;
            }
        }
        if (isPressed) {
            userFilmRepository.delete(userFilm);
        }
        return "redirect:/" + idUser + "/watchlist/";
    }

    @GetMapping("/{idUser}/watchlist/")
    public String openWatchlist(Model model, @PathVariable("idUser") long idUser) {
        if (idUser == 0) {
            return "redirect:/login/";
        }
        List<UserFilm> userFilm = userFilmRepository.findByUser(userRepository.findById(idUser).orElseThrow());
        List<Movies> moviesList = new ArrayList<>();
        for (UserFilm userFilm1 : userFilm) {
            moviesList.add(userFilm1.getMovies());
        }
        User user = userRepository.findById(idUser).orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("listOfMoviesAndTVSeries", moviesList);
        model.addAttribute("listOfMovies", userFilm);
        return "watchlist";
    }
}