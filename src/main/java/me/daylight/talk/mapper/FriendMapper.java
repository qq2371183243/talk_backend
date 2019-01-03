package me.daylight.talk.mapper;

import me.daylight.talk.model.Friend;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface FriendMapper {
    void insert(Friend record);
    void delete(@Param("phone") String phone,@Param("friendPhone") String friendPhone);
    void changeState(Friend friend);
    boolean isFriendExist(@Param("phone")String phone,@Param("friendPhone")String friendPhone);
    List<Friend> getFriendList(@Param("phone")String phone,@Param("time")Timestamp time);
    Friend getFriend(@Param("phone")String phone,@Param("friendPhone")String friendPhone);
    boolean hasNewFriend(@Param("phone")String phone);
    boolean isFriendDeleted(@Param("phone")String phone,@Param("friendPhone")String friendPhone);
}