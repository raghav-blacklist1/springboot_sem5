package com.raghav.moviesnow.modeldao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.raghav.moviesnow.models.MovieTag;

public interface MovieTagdao extends CrudRepository<MovieTag, Integer> {

    @Query("SELECT m FROM MovieTag m where movie_id = :id")
    public List<MovieTag> getall(@Param("id") int id);
}