package me.daylight.talk.service.impl;

import me.daylight.talk.mapper.FriendMapper;
import me.daylight.talk.model.Friend;
import me.daylight.talk.service.FriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service("friendService")
public class FriendServiceImpl implements FriendService{
    @Resource
    private FriendMapper friendMapper;

    @Override
    public void addFriend(Friend friend) {
        friendMapper.insert(friend);
    }

    @Override
    public void deleteFriend(String phone, String friendPhone) {
        friendMapper.delete(phone, friendPhone);
    }

    @Override
    public void changeState(Friend friend) {
        friendMapper.changeState(friend);
    }

    @Override
    public boolean isFriendExist(String phone, String friendPhone) {
        return friendMapper.isFriendExist(phone, friendPhone);
    }

    @Override
    public List<Friend> getFriendList(String phone,Timestamp time) {
        return friendMapper.getFriendList(phone, time);
    }

    @Override
    public Friend getFriend(String phone, String friendPhone) {
        return friendMapper.getFriend(phone, friendPhone);
    }

    @Override
    public boolean hasNewFriend(String phone) {
        return friendMapper.hasNewFriend(phone);
    }

    @Override
    public boolean isFriendDeleted(String phone, String friendPhone) {
        return friendMapper.isFriendDeleted(phone, friendPhone);
    }
}
