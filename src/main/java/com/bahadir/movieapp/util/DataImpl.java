package com.bahadir.movieapp.util;

import com.bahadir.movieapp.entity.Movie;
import com.bahadir.movieapp.entity.MovieComment;
import com.bahadir.movieapp.entity.User;
import com.bahadir.movieapp.service.GenreService;
import com.bahadir.movieapp.service.MovieService;
import com.bahadir.movieapp.service.UserService;
import com.bahadir.movieapp.util.data.Sample;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataImpl implements ApplicationRunner {

    private final MovieService movieService;
    private final GenreService genreService;
    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        saveAllMoviesTvMaze();
//        createUser();
    }
    public void saveAllMoviesTvMaze(){
        try {
            URL url = new URL("https://api.tvmaze.com/shows");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String value = "";
            value = bufferedReader.readLine();

            Sample[] array = new Gson().fromJson(value,Sample[].class);
            //Arrays.asList(array).forEach(System.out::println);
            Arrays.asList(array).forEach(x->{
                Movie movie = null;

                movie = Movie.builder()
                        .id(x.id)
                        .language(x.language)
                        .image(x.image.getOriginal())
                        .name(x.name)
                        .country(x.network==null ? null:x.network.country.name)
                        .rating(x.rating.average)
                        .summary(x.summary)
                        .premiered(LocalDate.parse(x.premiered))
                        .url(x.url)
                        .genres(genreService.createGenreWithName(x.genres))
                        .build();
                movieService.save(movie);
            });


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void createUser(){
//        User user = User.builder()
//                .email("@m@gmail.com")
//                .surname("ali")
//                .name("mehmet")
//                .phone("4664")
//                .password("2323")
//                .favGenres(genreService.createGenreWithName(List.of("Drama","Science-Fiction","Thriller","Horror")))
//                .favMovies(service.findAllByIds(List.of(1L,100L,50L,90L,120L,148L)))
//                .build();
//
//        user.setComments(List.of(
//                MovieComment.builder().content("iyi").movie(service.findById(75L)).user(user).build(),
//                MovieComment.builder().content("Harika").movie(service.findById(85L)).user(user).build(),
//                MovieComment.builder().content("Çok iyi değildi").movie(service.findById(15L)).user(user).build()
//        ));
//        userService.save(user);
//    }
public void createUser() {
    User user = User.builder().email("mert@hotmail.com").name("Mert").
            surname("Kaya").password("123").phone("123")
            .favGenres(genreService.createGenreWithName(List.of("Drama", "Science-Fiction", "Horror", "Documentry","AŞK")))
            .favMovies(movieService.findAllByIds(List.of(1l, 10L, 15L, 100L, 50L, 90L, 120L, 148L)))
            .build();


    User user1 = User.builder().email("merve@gmail.com").name("Merve")
            .favGenres(genreService.createGenreWithName(List.of("Drama", "Action", "Romance")))
            .favMovies(movieService.findAllByIds(List.of(8L, 3L, 17L, 18L, 9L, 85L, 78L, 127L, 1L, 120L, 85L)))
            .surname("Ozturk").password("123").phone("123")
            .build();

    user1.setComments(List.of(
            MovieComment.builder().content("iyi").date(LocalDate.now().minusYears(1)).user(user1).movie(movieService.findById(78L)).build(),
            MovieComment.builder().content("iyi").date(LocalDate.now().minusMonths(3)).user(user1).movie(movieService.findById(120L)).build(),
            MovieComment.builder().content("cok iyi").date(LocalDate.now().minusWeeks(2)).user(user1).movie(movieService.findById(1L)).build(),
            MovieComment.builder().content("idare eder").date(LocalDate.now().minusDays(4)).user(user1).movie(movieService.findById(136L)).build(),
            MovieComment.builder().content("iyi").date(LocalDate.now().minusDays(2)).user(user1).movie(movieService.findById(85L)).build()
    ));
    User user2 = User.builder().email("hasan@yandex.com").name("Hasan").

            surname("Bayindir").password("123").phone("123")
            .favGenres(genreService.createGenreWithName(List.of("War", "Western", "History", "Action")))
            .favMovies(movieService.findAllByIds(List.of(152l, 2L, 5L, 88L, 120L, 3L, 120L, 150L, 16L, 25L, 38L, 78L, 96L, 136L)))
            .build();
    user2.setComments((List.of(
            MovieComment.builder().content("iyi").date(LocalDate.now().minusMonths(8)).user(user2).movie(movieService.findById(2L)).build(),
            MovieComment.builder().content("iyi").date(LocalDate.now().minusMonths(5)).user(user2).movie(movieService.findById(136L)).build(),
            MovieComment.builder().content("cok iyi").date(LocalDate.now().minusMonths(4)).user(user2).movie(movieService.findById(106L)).build(),
            MovieComment.builder().content("idare eder").date(LocalDate.now().minusWeeks(10)).user(user2).movie(movieService.findById(26L)).build(),
            MovieComment.builder().content("iyi").date(LocalDate.now().minusWeeks(2)).user(user2).movie(movieService.findById(25L)).build()
    )));
    User user3 = User.builder().email("aras@gmail.com")
            .favGenres(genreService.createGenreWithName(List.of("Anime", "Comedy", "Supernatural", "Action")))
            .favMovies(movieService.findAllByIds(List.of(15l, 2L, 1L, 86L, 39L, 32L, 200L, 15L, 106L, 205L, 138L, 48L, 64L, 136L)))
            .name("Aras").
            surname("Gr").password("123").phone("123").favGenres(genreService.createGenreWithName(List.of("Anime", "Comedy", "Supernatural", "Action"))).build();
    user3.setComments(List.of(
            MovieComment.builder().content("berbat").date(LocalDate.now().minusWeeks(58)).user(user3).movie(movieService.findById(14L)).build(),
            MovieComment.builder().content("iyi").user(user3).date(LocalDate.now().minusWeeks(45)).movie(movieService.findById(136L)).build(),
            MovieComment.builder().content("cok iyi").date(LocalDate.now().minusMonths(2)).user(user3).movie(movieService.findById(106L)).build(),
            MovieComment.builder().content("berbat").date(LocalDate.now().minusWeeks(3)).user(user3).movie(movieService.findById(10L)).build(),
            MovieComment.builder().content("iyi").user(user3).date(LocalDate.now().minusDays(9)).movie(movieService.findById(15L)).build()
    ));

    User user4 = User.builder()
            .favMovies(movieService.findAllByIds(List.of(51l, 212L, 81L, 86L, 139L, 52L, 20L, 105L, 126L, 25L, 18L, 4L, 6L, 126L)))
            .email("didem@gmail.com").name("Didem")
            .surname("Sonmez").password("123").phone("123")
            .favGenres(genreService.createGenreWithName(List.of("Anime", "Action", "Mystery", "Supernatural"))).build();

    user4.setComments(List.of(
            MovieComment.builder().content("cok iyi").date(LocalDate.now().minusWeeks(3)).user(user4).movie(movieService.findById(51L)).build(),
            MovieComment.builder().content("�ahane").date(LocalDate.now().minusDays(12)).user(user4).movie(movieService.findById(105L)).build(),
            MovieComment.builder().content("idare eder").date(LocalDate.now().minusDays(3)).user(user4).movie(movieService.findById(1L)).build()
    ));

    User user5 = User.builder().email("admin@gmail.com").name("Mustafa").
            surname("Ozturk").password("admin").phone("123")
            .favGenres(genreService.createGenreWithName(List.of("Science-Fiction", "Drama", "Music", "Anime")))
            .favMovies(movieService.findAllByIds(List.of(14l, 22L, 106L, 88L, 104L, 66L, 20L, 104L, 126L, 25L, 13L, 4L, 69L, 47L, 200L)))

            .build();

    user5.setComments(List.of(
            MovieComment.builder().content("cok iyi").date(LocalDate.now().minusWeeks(50)).user(user5).movie(movieService.findById(14L)).build(),
            MovieComment.builder().content("iyi").date(LocalDate.now().minusMonths(10)).user(user5).movie(movieService.findById(66L)).build(),
            MovieComment.builder().content("berbat").date(LocalDate.now().minusDays(50)).user(user5).movie(movieService.findById(10L)).build(),
            MovieComment.builder().content("berbat").date(LocalDate.now().minusWeeks(5)).user(user5).movie(movieService.findById(108L)).build(),
            MovieComment.builder().content("iyi").date(LocalDate.now().minusDays(10)).user(user5).movie(movieService.findById(25L)).build()
    ));
    userService.saveAll(List.of(user,user1,user2,user3,user4,user5));
}

}