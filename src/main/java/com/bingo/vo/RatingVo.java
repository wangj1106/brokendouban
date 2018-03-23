package com.bingo.vo;

import com.bingo.domain.Rating;
import com.bingo.domain.User;

public class RatingVo {
    String username;
    Double rating;
    String comment;

    public RatingVo(Rating rating) {
        User user = rating.getUser();
        if (user!= null) {
            this.username = user.getUsername();
        } else {
            this.username = null;
        }

        this.rating = rating.getRating();
        this.comment = rating.getComment();

    }

    public String getUsername() {
        return username;
    }

    public Double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
