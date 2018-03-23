package com.bingo.domain;

import java.io.Serializable;

public class Rating {
    User user;
    Movie movie;
    Double rating;
    String comment;

    public Rating() {
        super();
    }

    public Rating(User user, Movie movie, Double rating, String comment) {
        this.user = user;
        this.movie = movie;
        this.rating = rating;
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
    }

    public Double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
