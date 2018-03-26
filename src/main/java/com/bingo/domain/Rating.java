package com.bingo.domain;

import java.util.Date;

public class Rating {
    private Integer rating_id;
    private Integer user_id;
    private Integer movie_id;
    private Double rating;
    private String comment;
    private Date create_time;
    private String username;
    private String movie_name;


    public Rating() {
        super();
    }

    public Rating(Integer rating_id, Integer user_id, Integer movie_id, Double rating, String comment, Date create_time, String username, String movie_name) {
        this.rating_id = rating_id;
        this.user_id = user_id;
        this.movie_id = movie_id;
        this.rating = rating;
        this.comment = comment;
        this.create_time = create_time;
        this.username = username;
        this.movie_name = movie_name;
    }

    public Rating(Integer user_id, Integer movie_id, Double rating, String comment) {
        this.user_id = user_id;
        this.movie_id = movie_id;
        this.rating = rating;
        this.comment = comment;
    }

    public Integer getRating_id() {
        return rating_id;
    }

    public void setRating_id(Integer rating_id) {
        this.rating_id = rating_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUser_name(String username) {
        this.username = username;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }
}
