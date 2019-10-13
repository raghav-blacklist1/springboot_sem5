package com.raghav.moviesnow.models;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Movie{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movie_id;

    private String name;
    private String description;

    private Double imdb_rating;
    private int runtime;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getImdb_rating() {
        return this.imdb_rating;
    }

    public void setImdb_rating(Double imdb_rating) {
        this.imdb_rating = imdb_rating;
    }

    public int getRuntime() {
        return this.runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getDesc() {
        return this.description;
    }

    public void setDesc(String description) {
        this.description = description;
    }
}