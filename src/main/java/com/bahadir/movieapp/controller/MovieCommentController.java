package com.bahadir.movieapp.controller;

import com.bahadir.movieapp.service.MovieCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/moviecomment")
public class MovieCommentController {

    private final MovieCommentService service;
}