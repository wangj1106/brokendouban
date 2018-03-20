package com.bingo.service;

import com.bingo.common.ServerResponse;
import com.bingo.domain.Movie;
import com.bingo.vo.MovieVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IMovieService {

    ServerResponse<String> addMovie(Movie movie);
    ServerResponse<String> deleteMovie(Integer movie_id);
    ServerResponse<Movie> getMovieInformation(Integer movie_id);
    ServerResponse<String> updateMovie(Movie movie);
    ServerResponse<PageInfo> getMovieList(int pageNum, int pageSize);
    ServerResponse<PageInfo> getMovieListByCateforty(int pageNum, int pageSize,String movie_keyword);
    ServerResponse<PageInfo> getMovieListByName(int pageNum, int pageSize,String movie_keyword);
    ServerResponse<String> checkMovieID(Integer movie_id);
    ServerResponse<List<Movie>> getSimilarMovieInfo(Integer movie_id);
}