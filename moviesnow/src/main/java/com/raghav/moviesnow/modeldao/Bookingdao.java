package com.raghav.moviesnow.modeldao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.raghav.moviesnow.models.Booking;

public interface Bookingdao extends CrudRepository<Booking, Integer> {
  
    @Query("SELECT b FROM Booking b where slot_id = :inp")
    public List<Booking> getObj(@Param("inp") int inp);

    @Query("SELECT b FROM Booking b where profile_id = :inp")
    public List<Booking> getObjbyProf(@Param("inp") int inp);

    @Query("SELECT CASE WHEN count(booking_id) > 0 THEN true ELSE false END FROM Booking where seat_no = :sno and slot_id = :sid")
    public boolean detects(@Param("sid") int sid, @Param("sno") int seatno);
}