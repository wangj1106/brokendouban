package com.bingo.controller.portal;

import com.bingo.common.Const;
import com.bingo.common.ServerResponse;
import com.bingo.domain.Rating;
import com.bingo.domain.User;
import com.bingo.service.IRatingService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@CrossOrigin
@RequestMapping("/rating/")
public class RatingController {
    @Autowired
    private IRatingService iRatingService;

    @RequestMapping(value="add.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Rating> addMovie(HttpSession session, int movieId, double rating, String comment){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        int userId =currentUser.getId();
        Rating ratingItem = new Rating(userId, movieId, rating, comment);

        ServerResponse<Rating> response = iRatingService.addRating(ratingItem);
        return response;
    }

    @RequestMapping(value="userRatingList.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> showList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        int userId =currentUser.getId();
        ServerResponse<PageInfo> response = iRatingService.getUserRatingList(pageNum, pageSize, userId);
        return response;
    }

    @RequestMapping(value="userRating.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Rating> showRating(HttpSession session, int movieId){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        int userId =currentUser.getId();
        ServerResponse<Rating> response = iRatingService.getUserRating(userId, movieId);
        return response;
    }

    @RequestMapping(value="movieRatingList.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> showMovieRatingList(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize, int movieId){
        ServerResponse<PageInfo> response = iRatingService.getMovieRatingList(pageNum, pageSize, movieId);
        return response;
    }

}
