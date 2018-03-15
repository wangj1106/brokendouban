package com.bingo.service.impl;

import com.bingo.common.ServerResponse;
import com.bingo.domain.Movie;
import com.bingo.repository.MovieRepository;
import com.bingo.service.IMovieService;
import com.bingo.vo.MovieVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements IMovieService{
    @Autowired
    private MovieRepository MovieRepository;

    @Override
    public ServerResponse<String> addMovie(Movie movie) {


        int checkResultCount = MovieRepository.checkMovieID(movie.getMovie_id());
        if(checkResultCount == 1){
            return ServerResponse.createByErrorMessage("该电影id已存在");
        }
        int resultCount = MovieRepository.insert(movie);
        if (resultCount==0)
        {
            return ServerResponse.createByErrorMessage("添加电影操作失败，请检查");
        }
        return ServerResponse.createBySuccessMessage("添加电影操作成功");
    }

    @Override
    public ServerResponse<String> deleteMovie(Integer movie_id) {
        int checkResultCount = MovieRepository.checkMovieID(movie_id);
        if(checkResultCount == 0){
            return ServerResponse.createByErrorMessage("该id电影不存在");
        }
        int resultCount = MovieRepository.deleteByMovie_id(movie_id);
        if (resultCount==0)
        {
            return ServerResponse.createBySuccess("删除电影操作失败，请检查");
        }
        return ServerResponse.createByErrorMessage("删除电影操作成功");

    }

    @Override
    public ServerResponse<MovieVo> getMovieInformation(Integer movie_id) {
        int checkResultCount = MovieRepository.checkMovieID(movie_id);
        if(checkResultCount == 0){
            return ServerResponse.createByErrorMessage("该id电影不存在");
        }
        Movie movie=MovieRepository.selectByMovie_id(movie_id);
        MovieVo movieVo=new MovieVo(movie);
        return ServerResponse.createBySuccess(movieVo);
    }

    @Override
    public ServerResponse<String> updateMovie(Movie movie) {
        int checkResultCount = MovieRepository.checkMovieID(movie.getMovie_id());
        if(checkResultCount == 0){
            return ServerResponse.createByErrorMessage("该id电影不存在");
        }
        Movie updateMovie = new Movie();
        updateMovie.setMovie_id(movie.getMovie_id());
        updateMovie.setMovie_actor(movie.getMovie_actor());
        updateMovie.setMovie_date(movie.getMovie_date());
        updateMovie.setMovie_director(movie.getMovie_director());
        updateMovie.setMovie_district(movie.getMovie_district());
        updateMovie.setMovie_imdbid(movie.getMovie_imdbid());
        updateMovie.setMovie_language(movie.getMovie_language());
        updateMovie.setMovie_time(movie.getMovie_time());
        updateMovie.setmovie_keyword(movie.getmovie_keyword());
        updateMovie.setmovie_name(movie.getmovie_name());
        int updateCount = MovieRepository.updateMovie(updateMovie);
        if(updateCount > 0){
            return ServerResponse.createBySuccess("更新电影信息成功");
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");

    }


    @Override
    public ServerResponse<PageInfo> getMovieList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> movieList = MovieRepository.selectList();
        List<MovieVo> movieVoList = new ArrayList<>();
        for(Movie movie : movieList) {
            MovieVo movieVo=new MovieVo(movie);
            movieVoList.add(movieVo);
        }

        PageInfo pageResult = new PageInfo(movieVoList);
        pageResult.setList(movieVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<PageInfo> getMovieListByCateforty(int pageNum, int pageSize,String movie_keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> movieList = MovieRepository.selectListByCategory(movie_keyword);
        List<MovieVo> movieVoList = new ArrayList<>();
        for(Movie movie : movieList) {
            MovieVo movieVo=new MovieVo(movie);
            movieVoList.add(movieVo);
        }

        PageInfo pageResult = new PageInfo(movieVoList);
        pageResult.setList(movieVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<PageInfo> getMovieListByName(int pageNum, int pageSize,String movie_name) {
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> movieList = MovieRepository.selectListByName(movie_name);
        List<MovieVo> movieVoList = new ArrayList<>();
        for(Movie movie : movieList) {
            MovieVo movieVo=new MovieVo(movie);
            movieVoList.add(movieVo);
        }

        PageInfo pageResult = new PageInfo(movieVoList);
        pageResult.setList(movieVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<String> checkMovieID(Integer movie_id) {
        int checkResultCount = MovieRepository.checkMovieID(movie_id);
        if(checkResultCount == 0){
            return ServerResponse.createBySuccessMessage("该电影id不存在");
        }
        else return ServerResponse.createBySuccessMessage("该电影id存在");
    }
}