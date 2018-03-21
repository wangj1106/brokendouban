package com.bingo.controller.backend;

import com.bingo.common.Const;
import com.bingo.common.ResponseCode;
import com.bingo.common.ServerResponse;
import com.bingo.domain.User;
import com.bingo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
@CrossOrigin
@Controller
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
        ServerResponse<User> response = iUserService.login(username, password);
        if (iUserService.checkAdminRole(response.getData()).isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
            return response;
        } else {
            return ServerResponse.createByErrorMessage("不是管理员,无法登录");
        }
    }

    @RequestMapping(value = "list.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getList(HttpSession session, @RequestParam(value = "pageNum", defaultValue
            = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {

            return iUserService.getUserList(pageNum, pageSize);
        } else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping(value = "delete.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteMovie(HttpSession session,Integer userId) {
        int checkResult = checkUser(session);
        if (checkResult == ResponseCode.NEED_LOGIN.getCode()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (checkResult == ResponseCode.ERROR.getCode()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "权限不足");
        }
        ServerResponse<String> response = iUserService.deleteUser(userId);
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

    @RequestMapping(value="get_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInformation(HttpSession session,Integer userId){
        int checkResult = checkUser(session);
        if (checkResult == ResponseCode.NEED_LOGIN.getCode()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (checkResult == ResponseCode.ERROR.getCode()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "权限不足");
        }

        ServerResponse<User> response = iUserService.getInformation(userId);
        return response;
    }

    @RequestMapping(value = "update_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> update_information(HttpSession session, User user){
        int checkResult = checkUser(session);
        if (checkResult == ResponseCode.NEED_LOGIN.getCode()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录管理员");
        }
        if (checkResult == ResponseCode.ERROR.getCode()) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), "权限不足");
        }
        ServerResponse<User> response = iUserService.updateInformation(user);

        return response;
    }

}
