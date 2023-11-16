package com.bahadir.movieapp.service;

import com.bahadir.movieapp.repository.IMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieCommentService {
    private final IMovieRepository repository;
}
