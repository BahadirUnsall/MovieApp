package com.bahadir.movieapp.service;

import com.bahadir.movieapp.entity.Movie;
import com.bahadir.movieapp.repository.IMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final IMovieRepository repository;

    public void save(Movie movie) {
        repository.save(movie);
    }
    public Movie findById(Long id){
        Optional<Movie> optionalMovie = repository.findById(id);
        if (optionalMovie.isPresent()){
            return optionalMovie.get();
        }else {
            throw new RuntimeException("Film bulunamadi!");
        }
    }
    public List<Movie> findAllByIds(List<Long> ids){
       return ids.stream().map(this::findById).collect(Collectors.toList());
    }

    public List<Movie> findAll() {
        return repository.findAll();
    }
}
