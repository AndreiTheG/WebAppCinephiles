package com.example.WebAppCinephiles.Controller;

import com.example.WebAppCinephiles.Models.*;
import com.example.WebAppCinephiles.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.List;

@Controller
public class HomeController {
    public MoviesAndTVSeriesRepository moviesAndTVSeriesRepository;
    public ActorRepository actorRepository;
    public String errorMessage;
    public String warningNameInput;
    public String warningSurnameInput;
    public String warningEmailInput;
    public String warningPhoneNumberInput;
    public String warningUsernameInput;
    public String warningPasswordInput;
    public UserRepository userRepository;
    public boolean gotRegistered = false;
    public boolean isDataCorrect = false;
    public long currentUserId;
    public FilmActorRepository filmActorRepository;
    public ReviewRepository reviewRepository;
    public CommentRepository commentRepository;
    public UserFilmRepository userFilmRepository;
    public MessageRepository messageRepository;
    public SimpMessagingTemplate messagingTemplate;


    @Autowired
    public HomeController(MoviesAndTVSeriesRepository moviesAndTVSeriesRepository, UserRepository userRepository,
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

    // Login
    @GetMapping("/login/")
    public String login(Model model) {
        gotRegistered = false;
        isDataCorrect = false;
        model.addAttribute("user", new User());
        model.addAttribute("message", errorMessage);
        errorMessage = null;
        return "login";
    }

    //Register
    @GetMapping("/register/")
    public String register(Model model) {
        gotRegistered = true;
        model.addAttribute("user", new User());
        model.addAttribute("warningNameMessage", warningNameInput);
        model.addAttribute("warningSurnameMessage", warningSurnameInput);
        model.addAttribute("warningEmailMessage", warningEmailInput);
        model.addAttribute("warningPhoneNumMessage", warningPhoneNumberInput);
        model.addAttribute("warningUsernameMessage", warningUsernameInput);
        model.addAttribute("warningPasswordMessage", warningPasswordInput);
        warningNameInput = null;
        warningSurnameInput = null;
        warningEmailInput = null;
        warningPhoneNumberInput= null;
        warningUsernameInput = null;
        warningPasswordInput = null;
        return "register";
    }

    // Verify if the user's account exists in database by verifiyng the details
    public void findUser(User user) {
        List<User> users = userRepository.findAll();
        for (User currentUser : users) {
            if (currentUser.getImageProfileSrc() == null) {
                currentUser.setImageProfileSrc("https://i.pinimg.com/474x/76/4d/59/764d59d32f61f0f91dec8c442ab052c5.jpg");
                userRepository.save(currentUser);
            }
        }
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user.getUsername())
                    && currentUser.getPassword().equals(user.getPassword())) {
                isDataCorrect = true;
                user.setId(currentUser.getId());
                user.setName(currentUser.getName());
                user.setSurname(currentUser.getSurname());
                user.setEmail(currentUser.getEmail());
                user.setPhoneNumber(currentUser.getPhoneNumber());
                user.setDescription(currentUser.getDescription());
                user.setImageProfileSrc(currentUser.getImageProfileSrc());
                break;
            }
        }
    }

    // This method will show the message warnings in the register page if the new user forgot to introduce a field.
    public void addWarningMessagesRegister(User user) {
        if (user.getName().isEmpty())
            warningNameInput = "*Name required";
        if (user.getSurname().isEmpty())
            warningSurnameInput = "*Surname required";
        if (user.getEmail().isEmpty())
            warningEmailInput = "*Email required";
        if (user.getPhoneNumber().isEmpty())
            warningPhoneNumberInput = "*Phone number required";
        if (user.getUsername().isEmpty())
            warningUsernameInput = "*Username required";
        if (user.getPassword().isEmpty())
            warningPasswordInput = "*Password required";
    }

    // Create and open the home page after the user has logged in or registered
    @PostMapping("/home/")
    public String verifyTheIntroducedData(Model model, @Validated User user) {
        findUser(user);
        if (gotRegistered) {
            if (user.getName().isEmpty() || user.getSurname().isEmpty() || user.getEmail().isEmpty()
                    || user.getPhoneNumber().isEmpty() || user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
                gotRegistered = false;
                addWarningMessagesRegister(user);
                return "redirect:/register/";
            } else
                userRepository.save(user);
        } else if (!isDataCorrect) {
            if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
                errorMessage = "You must write data!";
            } else {
                errorMessage = "Incorrect username or password!";
            }
            return "redirect:/login/";
        }
        errorMessage = null;
        this.currentUserId = user.getId();
        List<Movies> list = moviesAndTVSeriesRepository.findAll();
        list.sort(Comparator.comparing(Movies :: getNrReviews).reversed()
                .thenComparing(Comparator.comparing(Movies :: getRating).reversed()));
        model.addAttribute("user", user);
        model.addAttribute("listOfMoviesAndTVSeries", list);
        return "home";
    }

    // Display the home page
    @GetMapping("/home/{userId}/")
    public String goBackToMainPage(@PathVariable("userId") long idValue) {
        if (idValue == 0) {
            return "redirect:/login/";
        }
        this.currentUserId = idValue;
        return "redirect:/home/";
    }

    @GetMapping("/home/")
    public String openHomePage(Model model) {
        if (this.currentUserId == 0) {
            return "redirect:/login/";
        }
        List<Movies> list = moviesAndTVSeriesRepository.findAll();
        list.sort(Comparator.comparing(Movies :: getNrReviews).reversed()
                .thenComparing(Comparator.comparing(Movies :: getRating).reversed()));
        User user = userRepository.findById(this.currentUserId).orElseThrow();
        model.addAttribute("user", user);
        model.addAttribute("listOfMoviesAndTVSeries", list);
        return "home";
    }
}