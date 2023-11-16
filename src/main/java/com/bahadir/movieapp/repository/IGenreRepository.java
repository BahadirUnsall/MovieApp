package com.bahadir.movieapp.repository;

import com.bahadir.movieapp.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IGenreRepository  extends JpaRepository<Genre,Long> {
    Optional<Genre> findByName(String genreName);


}
