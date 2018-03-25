package com.bingo.repository;

import com.bingo.domain.Rating;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository {
    int insert(Rating record);
    List<Rating> selectUserRatingList(int user_id);
    int checkUserRating(@Param("user_id") int user_id, @Param("movie_id") int movie_id);
    Rating selectRatingByUserIdMovieId(@Param("user_id") int user_id, @Param("movie_id") int movie_id);
}
