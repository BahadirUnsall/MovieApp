package com.bahadir.movieapp.repository;

import com.bahadir.movieapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository  extends JpaRepository<Movie,Long> {
}
