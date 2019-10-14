package com.raghav.moviesnow.modeldao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.raghav.moviesnow.models.Movie;

public interface Moviedao extends CrudRepository<Movie, Integer> {

    @Query("SELECT m FROM Movie m")
    public List<Movie> getall();

    @Query("SELECT m FROM Movie m where m.name = :inp")
    public Movie getObj(@Param("inp") String inp);

    @Query("SELECT m FROM Movie m where m.movie_id = :inp")
    public Movie getObj(@Param("inp") int inp);
}