package me.daylight.talk.controller;

import me.daylight.talk.model.User;
import me.daylight.talk.model.UserWithAvater;
import me.daylight.talk.ret.RetResponse;
import me.daylight.talk.ret.RetResult;
import me.daylight.talk.service.LoginService;
import me.daylight.talk.service.UserService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private LoginService loginService;

    private Map<String,byte[]> avaterMap=new HashMap<>();

    @RequestMapping(value = "/getInfo")
    public RetResult<User> getUserInfo(String phone){
        if (!userService.isUserExist(phone)) {
            if (!loginService.isUserExist(phone))
                return RetResponse.makeErrRsp("账号不存在");
            return RetResponse.makeErrRsp("账号信息未完善，请先完善信息");
        }
        return RetResponse.makeOKRsp(userService.findUserByPhone(phone));
    }

    @RequestMapping("/queryUser")
    public RetResult<List<User>> queryUsers(String query){
        return RetResponse.makeOKRsp(userService.queryUsers(query));
    }

    @RequestMapping(value = "/changeInfo")
    public RetResult changeUserInfo(@RequestBody User user) {
        if (userService.isUserExist(user.getPhone()))
            userService.update(user);
        else {
            loginService.updateStateByPhone(user.getPhone(), 100);
            UserWithAvater userWithAvater=new UserWithAvater();
            userWithAvater.setUser(user);
            if (avaterMap.containsKey(user.getPhone())) {
                userWithAvater.setHeadImage(avaterMap.get(user.getPhone()));
                avaterMap.remove(user.getPhone());
            }
            userService.insert(userWithAvater);
        }
        return RetResponse.makeOKRsp("信息更改成功");
    }

    @RequestMapping("/updateInfo")
    public RetResult<List<User>> updateUserInfo(String phone,long mills){
        return RetResponse.makeOKRsp(userService.updateUserInfo(phone,new Timestamp(mills)));
    }

    @RequestMapping(value = "/getFriendsInfo")
    public RetResult<List<User>> getFriendLists(String phone,Long mills){
        return RetResponse.makeOKRsp(userService.getFriendsInfo(phone,new Timestamp(mills)));
    }
    /*
     *获取好友申请列表（谁要加我）
     */
    @RequestMapping(value = "/getRequestList")
    public RetResult<List<User>> getRequestList(String phone){
        return RetResponse.makeOKRsp(userService.getRequestList(phone));
    }
    /*
     *获取好友请求列表（我要加谁）
     */
    @RequestMapping(value = "/getRequireList")
    public RetResult<List<User>> getRequireList(String phone){
        return RetResponse.makeOKRsp(userService.getRequireList(phone));
    }

    @RequestMapping("/getAll")
    public RetResult<List<User>> getAll(){
        return RetResponse.makeOKRsp(userService.getAllUsers());
    }

    @RequestMapping("/uploadHead")
    public RetResult uploadHead(@RequestParam("file") MultipartFile file,String phone) throws IOException {
        if (!file.isEmpty()){
            if (userService.isUserExist(phone))
                userService.updateUserHeadImage(phone, file.getBytes());
            else
                avaterMap.put(phone,file.getBytes());
            return RetResponse.makeOKRsp("上传成功");
        }
        return RetResponse.makeErrRsp("操作失败");
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @RequestMapping("/headImage/{phone:.+}")
    public void getHeadImage(@PathVariable String phone, HttpServletResponse response) throws IOException {
        response.setContentType("image/png");
        byte[] data;
        if (userService.hasUserHeadImage(phone)){
            data=userService.getUserHeadImage(phone).getHeadImage();
        }else {
            InputStream inputStream=new ClassPathResource("static/ic_male_head.png").getInputStream();
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        }
        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
        stream.close();
    }
}
