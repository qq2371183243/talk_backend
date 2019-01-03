package me.daylight.talk.service;

import me.daylight.talk.model.UserLogin;

public interface LoginService {
    void register(UserLogin userLogin);
    void changePassword(String phone,String password);
    boolean checkPassword(String phone, String password);
    boolean isUserExist(String phone);
    int getStateByPhone(String phone);
    void updateStateByPhone(String phone,int state);
}
