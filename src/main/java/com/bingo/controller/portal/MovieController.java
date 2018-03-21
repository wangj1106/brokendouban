package com.bingo.controller.portal;

import com.bingo.common.ResponseCode;
import com.bingo.common.ServerResponse;
import com.bingo.domain.Movie;
import com.bingo.service.IMovieService;
import com.bingo.vo.MovieVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/movie/")
public class MovieController {

    @Autowired
    private IMovieService iMovieService;

    @RequestMapping(value="showlist.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse showMovieList(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, @RequestParam(value = "orderBy",defaultValue = "null")String orderBy)
    {
        ServerResponse<PageInfo> response = iMovieService.getMovieList(pageNum, pageSize, orderBy);
        return response;
    }
    @RequestMapping(value="showlistByCategory.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse show(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize , @RequestParam(value = "orderBy",defaultValue = "null")String orderBy,String movie_keyword)
    {
        ServerResponse<PageInfo> response = iMovieService.getMovieListByCateforty(pageNum, pageSize, orderBy, movie_keyword);
        return response;
    }

    @RequestMapping(value="showlistByName.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse show2(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,String movie_name)
    {
        ServerResponse<PageInfo> response = iMovieService.getMovieListByName(pageNum, pageSize,movie_name);
        return response;
    }

    @RequestMapping(value="show.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Movie> getMovieInformation(Integer movie_id){

        ServerResponse<Movie> response = iMovieService.getMovieInformation(movie_id);
        return response;
    }

    @RequestMapping(value = "showsimilarmovie.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Movie>> getSimilarMovieInfo(@RequestParam(value = "movieId",defaultValue = "35423") int movieId)
    {
        ServerResponse<List<Movie>> response=iMovieService.getSimilarMovieInfo(movieId);
        return response;
    }


}
