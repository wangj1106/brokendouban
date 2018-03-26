package com.bingo.controller.backend;

import com.bingo.common.Const;
import com.bingo.common.ResponseCode;
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

@Controller
@CrossOrigin
@RequestMapping("/manage/rating")
public class RatingManageController {
    @Autowired
    private IRatingService iRatingService;
    @Autowired
    private IUserService iUserService;

    @RequestMapping(value="ratingList.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> showList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {

            return iRatingService.getRatingList(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping(value="deleteRating.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteRating(HttpSession session, int ratingId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {

            return iRatingService.deleteRating(ratingId);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
}
