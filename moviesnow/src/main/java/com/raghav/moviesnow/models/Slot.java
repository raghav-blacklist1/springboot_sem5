package com.raghav.moviesnow.models;

import javax.persistence.*;
import java.util.Date;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity // This tells Hibernate to make a table out of this class
public class Slot{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int slot_id;

    private int start_time;
    private int end_time;
    private Date date;
    private int audi_num;

    public int getAudi_num() {
        return this.audi_num;
    }

    public void setAudi_num(int audi_num) {
        this.audi_num = audi_num;
    }

    public int getStart_time() {
        return this.start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return this.end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Movie movie;
}