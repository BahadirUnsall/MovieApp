package com.bahadir.movieapp.service;

import com.bahadir.movieapp.entity.Genre;
import com.bahadir.movieapp.repository.IGenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final IGenreRepository repository;

    public List<Genre> createGenreWithName(List<String> genres){
        List<Genre> genreList = new ArrayList<>();
        for (String genreName : genres) {
            Optional<Genre> genre = repository.findByName(genreName);
            if (genre.isPresent()){
                genreList.add(genre.get());
            }else {
                Genre genree = Genre.builder()
                        .name(genreName)
                        .build();
                repository.save(genree);
                genreList.add(genree);
            }
        }
        return genreList;
    }

}
