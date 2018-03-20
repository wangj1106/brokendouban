package com.bingo.controller.backend;

import com.bingo.common.Const;
import com.bingo.common.ResponseCode;
import com.bingo.common.ServerResponse;
import com.bingo.domain.Movie;
import com.bingo.domain.User;
import com.bingo.service.IMovieService;
import com.bingo.service.IUserService;
import com.bingo.vo.MovieVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
@RequestMapping("/manage/movie")
public class MovieManageController {

    @Autowired
    private IMovieService iMovieService;
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value="add.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> addMovie(HttpSession session,Movie movie){
       int checkResult = checkUser(session);
       if (checkResult==ResponseCode.NEED_LOGIN.getCode())
       { return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
       }
        if (checkResult==ResponseCode.ERROR.getCode())
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "权限不足");
        }

        ServerResponse<String> response = iMovieService.addMovie(movie);
        return response;

    }

    @RequestMapping(value="delete.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteMovie(HttpSession session,Integer movie_id){
        int checkResult = checkUser(session);
        if (checkResult==ResponseCode.NEED_LOGIN.getCode())
        { return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (checkResult==ResponseCode.ERROR.getCode())
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "权限不足");
        }
        ServerResponse<String> response = iMovieService.deleteMovie(movie_id);
        return response;
    }

    @RequestMapping(value="get_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<MovieVo> getMovieInformation(HttpSession session,Integer movie_id){
//        int checkResult = checkUser(session);
//        if (checkResult==ResponseCode.NEED_LOGIN.getCode())
//        { return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
//        }
//        if (checkResult==ResponseCode.ERROR.getCode())
//        {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "权限不足");
//        }
        ServerResponse<MovieVo> response = iMovieService.getMovieInformation(movie_id);
        return response;
    }

    @RequestMapping(value="update_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> updateMovie(HttpSession session,Movie movie){
        int checkResult = checkUser(session);
        if (checkResult==ResponseCode.NEED_LOGIN.getCode()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (checkResult==ResponseCode.ERROR.getCode())
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "权限不足");
        }
        ServerResponse<String> response = iMovieService.updateMovie(movie);
        return response;
    }

    @RequestMapping(value="list.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize)
    {    int checkResult = checkUser(session);
        if (checkResult==ResponseCode.NEED_LOGIN.getCode())
        { return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (checkResult==ResponseCode.ERROR.getCode())
        {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "权限不足");
        }
        ServerResponse<PageInfo> response = iMovieService.getMovieList(pageNum, pageSize);
        return response;
    }

    public int checkUser(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ResponseCode.NEED_LOGIN.getCode();
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {

            return ResponseCode.SUCCESS.getCode();

        }
        else
            return ResponseCode.ERROR.getCode();
    }
}
