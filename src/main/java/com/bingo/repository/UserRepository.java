package com.bingo.repository;

import com.bingo.domain.Movie;
import com.bingo.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    int checkEmail(String email);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    String selectQuestionByUsername(String username);

    int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    int updatePasswordByUsername(@Param("username") String username, @Param("passwordNew") String passwordNew);

    int checkPassword(@Param("password") String password, @Param("userId") Integer userId);

    int checkEmailByUserId(@Param(value = "email") String email, @Param("userId") Integer userId);

    List<User> selectList();

    List<Movie> selectRecommend(Integer id);

    int setUserComment(@Param(value = "user_id")Integer user_id,@Param(value = "movie_id")Integer movie_id,@Param(value = "rating") Double rating,@Param(value = "comment")String comment);

    int changeRecommend(Integer id);
}
