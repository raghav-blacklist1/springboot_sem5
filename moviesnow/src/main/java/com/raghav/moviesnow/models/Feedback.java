package com.raghav.moviesnow.models;

import javax.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity // This tells Hibernate to make a table out of this class
public class Feedback{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feed_id;

    public int getId() {
        return this.feed_id;
    }

    private int rating;
    private String comments;

    public int getRating() {
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Profile profile;

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
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
}
