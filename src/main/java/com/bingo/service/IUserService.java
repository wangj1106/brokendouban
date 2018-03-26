package com.bingo.service;

import com.bingo.common.ServerResponse;
import com.bingo.domain.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserService {

    ServerResponse<User> login(String username, String password);
    ServerResponse<String> register(User user);
    ServerResponse<String> checkValid(String str, String type);
    ServerResponse selectQuestion(String username);
    ServerResponse<String> checkAnswer(String username, String question, String answer);
    ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user);
    ServerResponse<User> updateInformation(User user);
    ServerResponse<User> getInformation(Integer userId);
    ServerResponse checkAdminRole(User user);
    ServerResponse<PageInfo> getUserList(int pageNum, int pageSize);
    ServerResponse<List> getRecommend(Integer userId);
    ServerResponse<List> getRecommend2(Integer userId);
    ServerResponse<String> deleteUser(Integer userId);
    ServerResponse<String> setUserComment(Integer movie_id, Integer user_id, Double rating, String comment);


}
