package me.daylight.talk.service.impl;

import me.daylight.talk.mapper.UserMapper;
import me.daylight.talk.model.User;
import me.daylight.talk.model.UserWithAvater;
import me.daylight.talk.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public void insert(UserWithAvater user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public User findUserByPhone(String phone) {
        return userMapper.findUserByPhone(phone);
    }

    @Override
    public boolean isUserExist(String phone) {
        return userMapper.isUserExist(phone);
    }

    @Override
    public List<User> updateUserInfo(String phone, Timestamp time) {
        return userMapper.updateUserInfo(phone, time);
    }

    @Override
    public List<User> getFriendsInfo(String phone,Timestamp time) {
        return userMapper.getFriendsInfo(phone,time);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public List<User> getRequestList(String phone) {
        return userMapper.getRequestList(phone);
    }

    @Override
    public List<User> getRequireList(String phone) {
        return userMapper.getRequireList(phone);
    }

    @Override
    public UserWithAvater getUserHeadImage(String phone) {
        return userMapper.getUserHeadImage(phone);
    }

    @Override
    public void updateUserHeadImage(String phone, byte[] headImage) {
        userMapper.updateUserHeadImage(phone, headImage);
    }

    @Override
    public boolean hasUserHeadImage(String phone) {
        return userMapper.hasUserHeadImage(phone);
    }

    @Override
    public List<User> queryUsers(String query) {
        return userMapper.queryUsers(query);
    }


}
