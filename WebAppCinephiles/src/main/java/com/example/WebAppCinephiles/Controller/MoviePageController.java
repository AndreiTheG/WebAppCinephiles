package com.example.WebAppCinephiles.Controller;

import com.example.WebAppCinephiles.Models.*;
import com.example.WebAppCinephiles.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class MoviePageController {
    public MoviesAndTVSeriesRepository moviesAndTVSeriesRepository;
    public ActorRepository actorRepository;
    public UserRepository userRepository;
    public FilmActorRepository filmActorRepository;
    public ReviewRepository reviewRepository;
    public CommentRepository commentRepository;
    public UserFilmRepository userFilmRepository;
    public MessageRepository messageRepository;
    public FilmGenreRepository filmGenreRepository;
    public long currentUserId;
    public long currentReviewId;

    @Autowired
    public MoviePageController(MoviesAndTVSeriesRepository moviesAndTVSeriesRepository, UserRepository userRepository,
                          ActorRepository actorRepository, FilmActorRepository filmActorRepository,
                          ReviewRepository reviewRepository, CommentRepository commentRepository,
                          UserFilmRepository userFilmRepository, MessageRepository messageRepository,
                               FilmGenreRepository filmGenreRepository) {
        this.moviesAndTVSeriesRepository = moviesAndTVSeriesRepository;
        this.userRepository = userRepository;
        this.actorRepository = actorRepository;
        this.filmActorRepository = filmActorRepository;
        this.reviewRepository = reviewRepository;
        this.commentRepository = commentRepository;
        this.userFilmRepository = userFilmRepository;
        this.messageRepository = messageRepository;
        this.filmGenreRepository = filmGenreRepository;
    }

    double calculateRating(List<Review> listOfRating) {
        double sum = 0, nrNonNullRatings = 0;
        for (Review review1 : listOfRating) {
            if (review1.getRating() > 0) {
                sum += review1.getRating();
                ++nrNonNullRatings;
            }
        }
        return sum / nrNonNullRatings;
    }

void saveAttributeModels(Model model, double average, List<Movies> listOfMovies,
                         Movies moviesAndTVSeries, User user, List<FilmActor> filmActor, Comment comment,
                         Review review, List<Review> listOfRating, List<Comment> comments,
                         UserFilmKey userFilmKey, List<FilmGenre> filmGenre) {
        int totalReviews = 0;
        for (Review review1 : listOfRating) {
            if (review1.getRating() > 0) {
                ++totalReviews;
            }
        }
        moviesAndTVSeries.setNrReviews(totalReviews);
        moviesAndTVSeriesRepository.save(moviesAndTVSeries);
        List<Actor> actors = new ArrayList<>();
        for (FilmActor filmActor1 : filmActor) {
            actors.add(filmActor1.getActor());
        }
        List<Genres> genres = new ArrayList<>();
        for (FilmGenre filmGenre1 : filmGenre) {
            genres.add(filmGenre1.getGenre());
        }
        model.addAttribute("average", String.format("%.2f", average));
        model.addAttribute("listOfMoviesAndTVSeries", listOfMovies);
        model.addAttribute("movie", moviesAndTVSeries);
        model.addAttribute("user", user);
        model.addAttribute("genres", genres);
        model.addAttribute("actors", actors);
        model.addAttribute("comment", comment);
        model.addAttribute("review", review);
        model.addAttribute("listOfRating", listOfRating);
        model.addAttribute("listOfComments", comments);
        model.addAttribute("userFilmKey", userFilmKey);
        model.addAttribute("totalReviews", totalReviews);
    }

    // Opens and save the modifications of movie's data (average rating)

    @PostMapping("/{idUser}/movie_page/{idMovie}/addFilm/")
    public String addFilmInWatchlist(@PathVariable("idUser") long idUser, @PathVariable("idMovie") long currentMovieId, UserFilmKey userFilmKey) {
        userFilmKey.setUserId(idUser);
        userFilmKey.setMovieId(currentMovieId);
        UserFilm userFilm = new UserFilm();
        userFilm.setId(userFilmKey);
        User user = userRepository.findById(userFilmKey.getUserId()).orElseThrow();
        userFilm.setUser(user);
        Movies movies = moviesAndTVSeriesRepository.findById(userFilm.getId().getMovieId()).orElseThrow();
        userFilm.setMovies(movies);
        userFilmRepository.save(userFilm);
        return "redirect:/" + idUser + "/movie_page/" + currentMovieId + "/";
    }

    @PostMapping("/{idUser}/movie_page/{idMovie}/")
    public String saveRating(@PathVariable("idUser") long idUser, @PathVariable("idMovie") long currentMovieId, Review review, Comment comment, UserFilmKey userFilmKey) {
        Movies movies = moviesAndTVSeriesRepository.findById(currentMovieId).orElseThrow();
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        review.setIdUser(idUser);
        review.setIdMovie(currentMovieId);
        boolean exists = false;
        List<Review> listOfReviews = reviewRepository.findAll();
        for (Review review1 : listOfReviews) {
            if (review1.getIdUser() == review.getIdUser() && review1.getIdMovie() == review.getIdMovie()) {
                exists = true;
                currentReviewId = review1.getIdReview();
                review.setIdReview(review1.getIdReview());
                if (review.getRating() != 0) {
                    review1.setRating(review.getRating());
                }
                break;
            }
        }
        comment.setIdUser(idUser);
        comment.setIdMovie(currentMovieId);
        comment.setUsername(userRepository.findById(idUser).orElseThrow().getUsername());
        comment.setDatePost(currentTimestamp);
        if (comment.getContent() != null && comment.getContent().length() > 0) {
            commentRepository.save(comment);
        }
        if (idUser == 0) {
            return "redirect:/login/";
        }
        List<Review> verifyReviews = reviewRepository.findAll();
        verifyReviews.add(review);
        Review currentReview = reviewRepository.findById(currentReviewId).orElseThrow();
        if (review.getRating() != 0) {
            currentReview.setRating(review.getRating());
        }
        reviewRepository.save(currentReview);
        moviesAndTVSeriesRepository.save(movies);
        return "redirect:/" + idUser + "/movie_page/" + currentMovieId + "/";
    }

    void verifyExistentReview(Review review) {
        boolean exists = false;
        List<Review> listOfReviews = reviewRepository.findAll();
        for (Review review1 : listOfReviews) {
            if (review1.getIdUser() == review.getIdUser() && review1.getIdMovie() == review.getIdMovie()) {
                exists = true;
                currentReviewId = review1.getIdReview();
                review.setIdReview(review1.getIdReview());
                review.setRating(review1.getRating());
                break;
            }
        }
        if (!exists) {
            reviewRepository.save(review);
        }
    }

    // Opens the current movie's page
    @GetMapping("/{idUser}/movie_page/{idMovie}/")
    public String openMoviePage(Model model, @PathVariable("idUser") long idUser, @PathVariable("idMovie") long currentMovieId) {
        System.out.println(idUser);
        this.currentUserId = idUser;
        if (currentUserId == 0) {
            return "redirect:/login/";
        }
        Review review = new Review();
        review.setIdUser(idUser);
        review.setIdMovie(currentMovieId);
        Comment comment = new Comment();
        comment.setIdUser(idUser);
        comment.setIdMovie(currentMovieId);
        UserFilmKey userFilmKey = new UserFilmKey();
        userFilmKey.setUserId(idUser);
        comment.setUsername(userRepository.findById(idUser).orElseThrow().getUsername());
        verifyExistentReview(review);
        Movies moviesAndTVSeries = moviesAndTVSeriesRepository.findById(currentMovieId).orElseThrow();
        List<Movies> listOfMovies = moviesAndTVSeriesRepository.findAll();
        List<Review> listOfRating = reviewRepository.findByIdMovie(currentMovieId);
        List<Comment> comments = commentRepository.findByIdMovie(currentMovieId);
        List<Comment> listOfComment = commentRepository.listComments(comments);
        double average = calculateRating(listOfRating);
        moviesAndTVSeries.setRating(average);
        List<FilmActor> filmActor = filmActorRepository.findByMovies(moviesAndTVSeries);
        List<FilmGenre> filmGenres = filmGenreRepository.findByMovies(moviesAndTVSeries);
        moviesAndTVSeriesRepository.save(moviesAndTVSeries);
        User user = userRepository.findById(idUser).orElseThrow();
        listOfMovies.sort(Comparator.comparing(Movies::getId));
        saveAttributeModels(model, average, listOfMovies, moviesAndTVSeries, user, filmActor,
                comment, review, listOfRating, listOfComment, userFilmKey, filmGenres);
        return "movieDetails";
    }
}