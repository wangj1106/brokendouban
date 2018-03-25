package com.bingo.service.impl;

import com.bingo.common.ServerResponse;
import com.bingo.domain.Rating;
import com.bingo.repository.RatingRepository;
import com.bingo.service.IRatingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService implements IRatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public ServerResponse<Rating> addRating(Rating rating) {
        int resultCount = ratingRepository.insert(rating);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("添加影评操作失败，请检查");
        }
        return ServerResponse.createBySuccessMessage("添加影评操作成功");
    }

    @Override
    public ServerResponse<PageInfo> getUserRatingList(int pageNum, int pageSize, int userId) {
        PageHelper.startPage(pageNum, pageSize, "create_time");
        List<Rating> userRatingList = ratingRepository.selectUserRatingList(userId);
        PageInfo pageResult = new PageInfo(userRatingList);
        pageResult.setList(userRatingList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<Rating> getUserRating(int userId, int movieId) {
        int checkResultCount = ratingRepository.checkUserRating(userId,movieId);
        if (checkResultCount == 0) {
            return ServerResponse.createByErrorMessage("未评价!");
        }
        Rating rating = ratingRepository.selectRatingByUserIdMovieId(userId,movieId);
        return ServerResponse.createBySuccess(rating);
    }

    @Override
    public ServerResponse<PageInfo> getRatingList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize, "create_time");
        List<Rating> ratingList = ratingRepository.selectRatingList();
        PageInfo pageResult = new PageInfo(ratingList);
        pageResult.setList(ratingList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<String> deleteRating(int ratingId) {
        int resultCount = ratingRepository.deleteByPrimaryKey(ratingId);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("删除评价操作失败，请检查");
        }
        return ServerResponse.createBySuccessMessage("删除评价操作成功");
    }
}
