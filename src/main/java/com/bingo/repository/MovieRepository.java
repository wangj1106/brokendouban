package com.bingo.repository;

import com.bingo.domain.Movie;
import com.bingo.vo.MovieVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository {

    int insert(Movie record);

    int deleteByMovie_id(Integer movie_id);

    Movie selectByMovie_id(Integer movie_id);

    int updateMovie(Movie movie);

    int checkMovieID(Integer movie_id);

    List<Movie> selectList();



}
