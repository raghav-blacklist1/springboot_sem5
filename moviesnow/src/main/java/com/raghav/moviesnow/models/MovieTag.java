package com.raghav.moviesnow.models;

import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity // This tells Hibernate to make a table out of this class
public class MovieTag{

    public String getTag_name() {
        return this.tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tag_id;
    private String tag_name;

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
}
