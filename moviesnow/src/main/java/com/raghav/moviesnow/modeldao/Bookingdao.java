package com.raghav.moviesnow.modeldao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.raghav.moviesnow.models.Booking;

public interface Bookingdao extends CrudRepository<Booking, Integer> {
  
    @Query("SELECT b FROM Booking b where slot_id = :inp")
    public List<Booking> getObj(@Param("inp") int inp);
}