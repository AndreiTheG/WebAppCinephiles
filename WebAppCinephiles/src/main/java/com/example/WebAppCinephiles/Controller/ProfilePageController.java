package com.example.WebAppCinephiles.Controller;

import com.example.WebAppCinephiles.Models.Movies;
import com.example.WebAppCinephiles.Models.User;
import com.example.WebAppCinephiles.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProfilePageController {
    public MoviesAndTVSeriesRepository moviesAndTVSeriesRepository;
    public ActorRepository actorRepository;
    public UserRepository userRepository;
    public FilmActorRepository filmActorRepository;
    public ReviewRepository reviewRepository;
    public CommentRepository commentRepository;
    public UserFilmRepository userFilmRepository;
    public MessageRepository messageRepository;
    public SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ProfilePageController(MoviesAndTVSeriesRepository moviesAndTVSeriesRepository, UserRepository userRepository,
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
    @GetMapping("/profile_page/{idUser}/update_description/")
    public String updateDescription(Model model, @PathVariable("idUser") long idUser) {
        if (idUser == 0) {
            return "redirect:/login/";
        }
        User user = userRepository.findById(idUser).orElseThrow();
//        List<Movies> list = moviesAndTVSeriesRepository.findAll();
//        model.addAttribute("listOfMoviesAndTVSeries", list);
        model.addAttribute("user", user);
        return "profilePageDescription";
    }

    // Opens and add the modifications of the profile page
    @PostMapping("/profile_page/{idUser}/")
    public String PostProfilePage(Model model, User user, @PathVariable("idUser") long idUser) {
        if (idUser == 0) {
            return "redirect:/login/";
        }
        User currentUser = userRepository.findById(idUser).orElseThrow();
        user.setId(idUser);
        if (user.getPassword().length() != 0) {
            currentUser.setPassword(user.getPassword());
        }
        if (user.getName().length() != 0) {
            currentUser.setName(user.getName());
        }
        if (user.getSurname().length() != 0) {
            currentUser.setSurname(user.getSurname());
        }
        if (user.getEmail().length() != 0) {
            currentUser.setEmail(user.getEmail());
        }
        if (user.getPhoneNumber().length() != 0) {
            currentUser.setPhoneNumber(user.getPhoneNumber());
        }
        if (user.getUsername().length() != 0) {
            currentUser.setUsername(user.getUsername());
        }
        currentUser.setDescription(user.getDescription());
        userRepository.save(currentUser);
//        List<Movies> list = moviesAndTVSeriesRepository.findAll();
//        model.addAttribute("listOfMoviesAndTVSeries", list);
        model.addAttribute("user", currentUser);
        return "profilePage";
    }

    // Opens and displays the profile page
    @GetMapping("/profile_page/{idUser}/")
    public String openProfilePage(Model model, @PathVariable("idUser") long idUser) {
        if (idUser== 0) {
            return "redirect:/login/";
        }
        User user = userRepository.findById(idUser).orElseThrow();
//        List<Movies> list = moviesAndTVSeriesRepository.findAll();
//        model.addAttribute("listOfMoviesAndTVSeries", list);
        model.addAttribute("user", user);
        return "profilePage";
    }
}