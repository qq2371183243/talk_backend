package me.daylight.talk.service;

import me.daylight.talk.model.Friend;

import java.sql.Timestamp;
import java.util.List;

public interface FriendService {
    void addFriend(Friend friend);
    void deleteFriend(String phone,String friendPhone);
    void changeState(Friend friend);
    boolean isFriendExist(String phone,String friendPhone);
    List<Friend> getFriendList(String phone,Timestamp mills);
    Friend getFriend(String phone,String friendPhone);
    boolean hasNewFriend(String phone);
    boolean isFriendDeleted(String phone,String friendPhone);
}
