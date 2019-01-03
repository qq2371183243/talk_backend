package me.daylight.talk.service.impl;

import me.daylight.talk.mapper.UserLoginMapper;
import me.daylight.talk.model.UserLogin;
import me.daylight.talk.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
    @Resource
    private UserLoginMapper userLoginMapper;

    @Override
    public void register(UserLogin userLogin) {
        userLoginMapper.insert(userLogin);
    }

    @Override
    public void changePassword(String phone, String password) {
        userLoginMapper.changePassword(phone, password);
    }

    @Override
    public boolean checkPassword(String phone, String password) {
        return userLoginMapper.checkPassword(phone,password);
    }

    @Override
    public boolean isUserExist(String phone) {
        return userLoginMapper.isUserExist(phone);
    }

    @Override
    public int getStateByPhone(String phone) {
        return userLoginMapper.getStateByPhone(phone);
    }

    @Override
    public void updateStateByPhone(String phone,int state) {
        userLoginMapper.updateStateByPhone(phone,state);
    }
}
