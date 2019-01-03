package me.daylight.talk.service;

import me.daylight.talk.model.User;
import me.daylight.talk.model.UserWithAvater;

import java.sql.Timestamp;
import java.util.List;

public interface UserService {
    void insert(UserWithAvater user);

    void update(User user);

    User findUserByPhone(String phone);

    boolean isUserExist(String phone);

    List<User> updateUserInfo(String phone,Timestamp time);

    List<User> getFriendsInfo(String phone, Timestamp time);

    List<User> getAllUsers();

    List<User> getRequestList(String phone);

    List<User> getRequireList(String phone);

    UserWithAvater getUserHeadImage(String phone);

    void updateUserHeadImage(String phone,byte[] headImage);

    boolean hasUserHeadImage(String phone);

    List<User> queryUsers(String query);
}
