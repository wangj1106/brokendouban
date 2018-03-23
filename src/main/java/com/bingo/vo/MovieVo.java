package com.bingo.vo;

import com.bingo.domain.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieVo {

    private Integer movie_id;

    private String movie_name;

    private String movie_director;

    private List<String> movie_actor;

    private String movie_language;

    private Integer type_id;

    private String movie_district;

    private Integer movie_date;

    private List<String> movie_keyword;

    private String pic_url;

    private String movie_imdbid;

    private Integer movie_time;

    private Integer movie_numVotes;

    private Double movie_rating;

    private Double movie_star;

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_director() {
        return movie_director;
    }

    public void setMovie_director(String movie_director) {
        this.movie_director = movie_director;
    }

    public List<String> getMovie_actor() {
        return movie_actor;
    }

    public void setMovie_actor(List<String> movie_actor) {
        this.movie_actor = movie_actor;
    }

    public String getMovie_language() {
        return movie_language;
    }

    public void setMovie_language(String movie_language) {
        this.movie_language = movie_language;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getMovie_district() {
        return movie_district;
    }

    public void setMovie_district(String movie_district) {
        this.movie_district = movie_district;
    }

    public Integer getMovie_date() {
        return movie_date;
    }

    public void setMovie_date(Integer movie_date) {
        this.movie_date = movie_date;
    }

    public List<String> getMovie_keyword() {
        return movie_keyword;
    }

    public void setMovie_keyword(List<String> movie_keyword) {
        this.movie_keyword = movie_keyword;
    }

    public String getPic_url() {
        return pic_url;
    }

    public String getMovie_imdbid() {
        return movie_imdbid;
    }

    public void setMovie_imdbid(String movie_imdbid) {
        this.movie_imdbid = movie_imdbid;
    }

    public Integer getMovie_time() {
        return movie_time;
    }

    public void setMovie_time(Integer movie_time) {
        this.movie_time = movie_time;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public Integer getMovie_numVotes() {
        return movie_numVotes;
    }

    public void setMovie_numVotes(Integer movie_numVotes) {
        this.movie_numVotes = movie_numVotes;
    }

    public Double getMovie_rating() {
        return movie_rating;
    }

    public void setMovie_rating(Double movie_rating) {
        this.movie_rating = movie_rating;
    }

    public Double getMovie_star() {
        return movie_star;
    }

    public void setMovie_star(Double movie_star) {
        this.movie_star = movie_star;
    }

    public MovieVo(Movie movie) {
        this.movie_id = movie.getMovie_id();
        this.movie_name = movie.getmovie_name();
        this.movie_director = movie.getMovie_director();
        if (movie.getMovie_actor() != null) {
            this.movie_actor = new ArrayList<>();
            movie_actor = Arrays.asList(movie.getMovie_actor().split(","));
        } else {
            this.movie_actor = null;
        }
        this.movie_language = movie.getMovie_language();
        this.type_id = movie.getType_id();
        this.movie_district = movie.getMovie_district();
        this.movie_date = movie.getMovie_date();
        if (movie.getMovie_actor() != null) {
            this.movie_keyword = new ArrayList<>();
            movie_keyword = Arrays.asList(movie.getmovie_keyword().split(","));
        } else {
            this.movie_keyword = null;
        }
        this.pic_url = movie.getPic_url();
        this.movie_imdbid = movie.getMovie_imdbid();
        this.movie_time = movie.getMovie_time();
        this.movie_rating = movie.getMovie_rating() / 2;
        this.movie_numVotes = movie.getMovie_numVotes();
        this.movie_star = (Math.rint(movie.getMovie_rating())) / 2;

    }

    public MovieVo() {
        super();
    }
}
