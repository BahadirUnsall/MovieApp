package com.bahadir.movieapp.repository;

import com.bahadir.movieapp.entity.MovieComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieCommentRepository  extends JpaRepository<MovieComment,Long> {
}
