package com.raghav.moviesnow.modeldao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.raghav.moviesnow.models.Slot;

public interface Slotdao extends CrudRepository<Slot, Integer> {

    @Query("SELECT s FROM Slot s where movie_id = :id")
    public List<Slot> getall(@Param("id") int id);
    
    @Query("SELECT s FROM Slot s where s.slot_id = :id")
    public Slot getbySlotid(@Param("id") int id);
}