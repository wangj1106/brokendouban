package com.bingo.service;

import com.bingo.common.ServerResponse;
import com.bingo.domain.Rating;
import com.github.pagehelper.PageInfo;

public interface IRatingService {
    ServerResponse<Rating> addRating(Rating rating);
    ServerResponse<PageInfo> getUserRatingList(int pageNum, int pageSize, int userId);
    ServerResponse<Rating> getUserRating(int userId, int movieId);
}
