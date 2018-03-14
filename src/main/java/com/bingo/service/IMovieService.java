package com.bingo.service;

import com.bingo.common.ServerResponse;
import com.bingo.domain.Movie;
import com.bingo.vo.MovieVo;
import com.github.pagehelper.PageInfo;

public interface IMovieService {

    ServerResponse<String> addMovie(Movie movie);
    ServerResponse<String> deleteMovie(Integer movie_id);
    ServerResponse<MovieVo> getMovieInformation(Integer movie_id);
    ServerResponse<String> updateMovie(Movie movie);
    ServerResponse<PageInfo> getMovieList(int pageNum, int pageSize);
    ServerResponse<PageInfo> getMovieListByCateforty(int pageNum, int pageSize,String movie_keyword);
    ServerResponse<String> checkMovieID(Integer movie_id);
}