package com.bingo.controller.portal;

import com.bingo.common.ResponseCode;
import com.bingo.common.ServerResponse;
import com.bingo.service.IMovieService;
import com.bingo.vo.MovieVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/movie/")
public class MovieController {

    @Autowired
    private IMovieService iMovieService;

    @RequestMapping(value="showlist.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse showMovieList(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize)
    {
        ServerResponse<PageInfo> response = iMovieService.getMovieList(pageNum, pageSize);
        return response;
    }

    @RequestMapping(value="show.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<MovieVo> getMovieInformation(Integer movie_id){

        ServerResponse<MovieVo> response = iMovieService.getMovieInformation(movie_id);
        return response;
    }

}
