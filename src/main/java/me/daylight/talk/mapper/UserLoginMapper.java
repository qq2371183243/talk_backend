package me.daylight.talk.mapper;

import me.daylight.talk.model.UserLogin;
import org.apache.ibatis.annotations.Param;

public interface UserLoginMapper {
    void deleteByPhone(String phone);

    void insert(UserLogin record);

    void changePassword(@Param("phone") String phone,@Param("password") String password);

    boolean checkPassword(@Param("phone") String phone,@Param("password") String password);

    boolean isUserExist(@Param("phone") String phone);

    int getStateByPhone(@Param("phone") String phone);

    void updateStateByPhone(@Param("phone") String phone,@Param("state") int state);
}