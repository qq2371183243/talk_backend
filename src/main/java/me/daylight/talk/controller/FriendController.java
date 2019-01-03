package me.daylight.talk.controller;

import me.daylight.talk.model.Friend;
import me.daylight.talk.ret.RetResponse;
import me.daylight.talk.ret.RetResult;
import me.daylight.talk.service.FriendService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/friend")
public class FriendController {
    @Resource
    private FriendService friendService;

    @RequestMapping("/add")
    public RetResult addFriend(@RequestBody Friend friend){
        if (friendService.isFriendExist(friend.getPhone(),friend.getFriendPhone()))
            return RetResponse.makeErrRsp("已添加该好友,请勿重复添加");
        friend.setState(-100);
        if (friendService.isFriendDeleted(friend.getPhone(),friend.getFriendPhone()))
            friendService.changeState(friend);
        else
            friendService.addFriend(friend);
        return RetResponse.makeOKRsp("已发送添加请求");
    }

    @RequestMapping("/accept")
    public RetResult<Friend> changeState(String phone,String friendPhone){
        Friend friend=new Friend();
        friend.setPhone(phone);
        friend.setFriendPhone(friendPhone);
        friend.setState(100);
        friend.setTime(new Date().getTime());
        friendService.changeState(friend);

        return RetResponse.makeOKRsp("添加好友成功",friendService.getFriend(phone, friendPhone));
    }

    @RequestMapping("/delete")
    public RetResult deleteFriend(String phone,String friendPhone){
        if (!friendService.isFriendExist(phone,friendPhone))
            return RetResponse.makeErrRsp("该好友不存在");
        friendService.deleteFriend(phone, friendPhone);
        return RetResponse.makeOKRsp("删除成功");
    }

    @RequestMapping("/getFriendList")
    public RetResult<List<Friend>> getFriendList(String phone,long mills){
        return RetResponse.makeOKRsp(friendService.getFriendList(phone,new Timestamp(mills)));
    }

    @RequestMapping("/hasNewFriend")
    public RetResult<Boolean> hasNewFriend(String phone){
        return RetResponse.makeOKRsp(friendService.hasNewFriend(phone));
    }
}
