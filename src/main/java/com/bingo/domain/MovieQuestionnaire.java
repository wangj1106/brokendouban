package com.bingo.domain;

public class MovieQuestionnaire {
    private Integer movie_id;
    //    @Field
    private String movie_name;
    //    @Field
    private String movie_director;

    private String movie_actor;

    private String movie_district;

    private String movie_year;

    private String movie_keyword;

    private String pic_url;

    private String movie_introduction;

    public MovieQuestionnaire(Integer movie_id, String movie_name, String movie_director, String movie_actor, String movie_district, String movie_year, String movie_keyword, String pic_url, String movie_introduction) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_director = movie_director;
        this.movie_actor = movie_actor;
        this.movie_district = movie_district;
        this.movie_year = movie_year;
        this.movie_keyword = movie_keyword;
        this.pic_url = pic_url;
        this.movie_introduction = movie_introduction;
    }

    public MovieQuestionnaire() {
        super();
    }

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

    public String getMovie_actor() {
        return movie_actor;
    }

    public void setMovie_actor(String movie_actor) {
        this.movie_actor = movie_actor;
    }

    public String getMovie_district() {
        return movie_district;
    }

    public void setMovie_district(String movie_district) {
        this.movie_district = movie_district;
    }

    public String getMovie_year() {
        return movie_year;
    }

    public void setMovie_year(String movie_year) {
        this.movie_year = movie_year;
    }

    public String getMovie_keyword() {
        return movie_keyword;
    }

    public void setMovie_keyword(String movie_keyword) {
        this.movie_keyword = movie_keyword;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getMovie_introduction() {
        return movie_introduction;
    }

    public void setMovie_introduction(String movie_introduction) {
        this.movie_introduction = movie_introduction;
    }
}
