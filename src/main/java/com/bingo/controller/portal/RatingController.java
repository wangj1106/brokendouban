package com.bingo.controller.portal;

import com.bingo.common.Const;
import com.bingo.common.ServerResponse;
import com.bingo.domain.Rating;
import com.bingo.domain.User;
import com.bingo.service.IRatingService;
import com.bingo.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@CrossOrigin
@RequestMapping("/rating/")
public class RatingController {
    @Autowired
    private IRatingService iRatingService;
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value="add.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Rating> addMovie(HttpSession session, int movieId, double rating, String comment){

        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if(currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        int userId =currentUser.getId();
        try {
            FileOutputStream fs = new FileOutputStream(new File("D:\\data\\log.txt"), true); //在该文件的末尾添加内容
            fs.write((String.valueOf(userId) + "::" + String.valueOf(movieId) + "::" + String.valueOf(rating) + "\n").getBytes());
            fs.flush();   //清空缓存里的数据，并通知底层去进行实际的写操作
            fs.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String portrait=null;
        Rating ratingItem = new Rating(userId, movieId, rating, comment,portrait);

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
