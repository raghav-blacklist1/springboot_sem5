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

    public int getSlot_id() {
        return this.slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    private int start_hour;

    private int start_min;
    private Date date;
    private int audi_num;

    public int getAudi_num() {
        return this.audi_num;
    }

    public void setAudi_num(int audi_num) {
        this.audi_num = audi_num;
    }

    public int getStart_hour() {
        return this.start_hour;
    }

    public void setStart_hour(int start_hour) {
        this.start_hour = start_hour;
    }

    public int getStart_min() {
        return this.start_min;
    }

    public void setStart_min(int start_min) {
        this.start_min = start_min;
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

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Transient
    public String hh;

    @Transient
    public String mm;

    public String getHh() {
        return this.hh;
    }

    public void setHh(String hh) {
        this.hh = hh;
    }

    public String getMm() {
        return this.mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }
}
