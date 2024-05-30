package com.example.WebAppCinephiles.Controller;


import com.example.WebAppCinephiles.Models.Messages;
import com.example.WebAppCinephiles.Models.User;
import com.example.WebAppCinephiles.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {
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
    public ChatController(MoviesAndTVSeriesRepository moviesAndTVSeriesRepository, UserRepository userRepository,
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

    @MessageMapping("/ws")
    @SendTo("/topic/public")
    public Messages sendMessage(@Payload Messages messages) {
        messagingTemplate.convertAndSend("/ws", messages);
        messages.setContent(messages.getContent().trim());
        if (messages.getContent().length() > 0) {
            messageRepository.save(messages);
        }
        return messages;
    }

    public void displayAttributes(Model model, long idUserSender, User user,
                                  User friend, Messages message, List<Messages> messages) {
        List<User> listOfUsers = userRepository.findFriends(idUserSender);
        model.addAttribute("user", user);
        model.addAttribute("friend", friend);
        model.addAttribute("listOfUsers", listOfUsers);
        model.addAttribute("message", message);
        model.addAttribute("messages", messages);
    }

    @GetMapping("/chat/{idUserSender}/")
    public String openChatWindow(Model model, @PathVariable("idUserSender") long idUserSender) {
        if (idUserSender == 0) {
            return "redirect:/login/";
        }
        User user = userRepository.findById(idUserSender).orElseThrow();
        User friend = new User();
        List<Messages> messages = messageRepository.findByUserIdSender(user.getId());
        Messages message = new Messages();
        message.setUserIdSender((int)idUserSender);
        displayAttributes(model, idUserSender, user, friend, message, messages);
        return "chat";
    }

    @GetMapping("/chat/{idUserSender}/sends_to/{idUserReceiver}/")
    public String chatWithAFriend(Model model, Messages message,
                                  @PathVariable("idUserSender") long idUserSender,
                                  @PathVariable("idUserReceiver") long idUserReceiver) {
        if (idUserSender == 0) {
            return "redirect:/login/";
        }
        User user = userRepository.findById(idUserSender).orElseThrow();
        User friend = userRepository.findById(idUserReceiver).orElseThrow();
        List<Messages> allMessages = messageRepository.findAll();
        List<Messages> messages = new ArrayList<>();
        for (Messages message1 : allMessages) {
            if ((message1.getUserIdSender() == idUserSender
                    && message1.getUserIdReceiver() == idUserReceiver)
                    || (message1.getUserIdSender() == idUserReceiver
                    && message1.getUserIdReceiver() == idUserSender)) {
                messages.add(message1);
            }
        }
        message.setUserIdSender(idUserSender);
        message.setUserIdReceiver(idUserReceiver);
        displayAttributes(model, idUserSender, user, friend, message, messages);
        return "chat";
    }
}