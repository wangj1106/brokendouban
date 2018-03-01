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

    private Integer pic_id;

    private String movie_imdbid;

    private Integer movie_time;

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

    public Integer getPic_id() {
        return pic_id;
    }

    public void setPic_id(Integer pic_id) {
        this.pic_id = pic_id;
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

    public MovieVo(Movie movie) {
        this.movie_id = movie.getMovie_id();
        this.movie_name = movie.getmovie_name();
        this.movie_director = movie.getMovie_director();
        this.movie_actor = new ArrayList<>();
        movie_actor=Arrays.asList(movie.getMovie_actor().split(","));
        this.movie_language = movie.getMovie_language();
        this.type_id = movie.getType_id();
        this.movie_district = movie.getMovie_district();
        this.movie_date = movie.getMovie_date();
        this.movie_keyword = new ArrayList<>();
        movie_keyword=Arrays.asList(movie.getmovie_keyword().split(","));
        this.pic_id = movie.getPic_id();
        this.movie_imdbid = movie.getMovie_imdbid();
        this.movie_time = movie.getMovie_time();
    }

    public MovieVo() {
        super();
    }
}
