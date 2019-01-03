package me.daylight.talk.mapper;

import me.daylight.talk.model.User;
import me.daylight.talk.model.UserWithAvater;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface UserMapper {
    int insert(UserWithAvater record);

    void update(User record);

    User findUserByPhone(String phone);

    List<User> getAllUsers();

    boolean isUserExist(@Param("phone") String phone);

    List<User> updateUserInfo(@Param("phone")String phone,@Param("time")Timestamp time);

    List<User> getFriendsInfo(@Param("phone")String phone, @Param("time")Timestamp time);

    List<User> getRequestList(@Param("phone")String phone);

    List<User> getRequireList(@Param("phone")String phone);

    UserWithAvater getUserHeadImage(@Param("phone")String phone);

    void updateUserHeadImage(@Param("phone")String phone,@Param("headImage")byte[] headImage);

    boolean hasUserHeadImage(@Param("phone")String phone);

    List<User> queryUsers(@Param("query")String query);
}