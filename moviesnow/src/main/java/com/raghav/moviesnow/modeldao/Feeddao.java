package com.raghav.moviesnow.modeldao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.raghav.moviesnow.models.Feedback;

public interface Feeddao extends CrudRepository<Feedback, Integer> {

    @Query("SELECT feed FROM Feedback feed where movie_id = :inp")
    public List<Feedback> getreviews(@Param("inp") int inp);

}