package com.bingo.domain;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//@Document(indexName="bingo",type="movie",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class Movie implements Serializable {
//    @Id
    private Integer movie_id;
//    @Field
    private String movie_name;
//    @Field
    private String movie_director;

    private String movie_actor;

    private String  movie_language;

    private Integer type_id;

    private String movie_district;

    private Integer movie_date;

    private String movie_keyword;

    private String pic_url;

    private String movie_imdbid;

    private Integer movie_time;

    public Movie(Integer movie_id, String movie_name, String movie_director, String movie_actor, String movie_language, Integer type_id, String movie_district, Integer movie_date, String movie_keyword, String pic_url, String movie_imdbid, Integer movie_time) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_director = movie_director;
        this.movie_actor = movie_actor;
        this.movie_language = movie_language;
        this.type_id = type_id;
        this.movie_district = movie_district;
        this.movie_date = movie_date;
        this.movie_keyword = movie_keyword;
        this.pic_url = pic_url;
        this.movie_imdbid = movie_imdbid;
        this.movie_time = movie_time;
    }

    public Movie() {
        super();
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public String getmovie_name() {
        return movie_name;
    }

    public void setmovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_director() {
        return movie_director;
    }

    public void setMovie_director(String movie_director) {
        this.movie_director = movie_director;
    }

    public String getMovie_actor() {
        return movie_actor;
    }

    public void setMovie_actor(String movie_actor) {
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

    public String getmovie_keyword() {
        return movie_keyword;
    }

    public void setmovie_keyword(String movie_keyword) {
        this.movie_keyword = movie_keyword;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
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
}
