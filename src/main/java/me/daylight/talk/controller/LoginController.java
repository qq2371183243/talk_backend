package me.daylight.talk.controller;

import me.daylight.talk.model.UserLogin;
import me.daylight.talk.ret.RetResponse;
import me.daylight.talk.ret.RetResult;
import me.daylight.talk.service.LoginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userLogin")
public class LoginController {
    @Resource
    private LoginService loginService;

    @RequestMapping(value = "/register")
    public RetResult register(UserLogin userLogin){
        if (loginService.isUserExist(userLogin.getPhone()))
            return RetResponse.makeErrRsp("账号已存在");
        userLogin.setState(-100);
        loginService.register(userLogin);
        return RetResponse.makeOKRsp("注册成功");
    }

    @RequestMapping(value = "/login")
    public RetResult<Integer> login(String phone, String password){
        if (!loginService.isUserExist(phone))
            return RetResponse.makeErrRsp("账号不存在");
        if (!loginService.checkPassword(phone, password))
            return RetResponse.makeErrRsp("密码错误");
        return RetResponse.makeOKRsp(loginService.getStateByPhone(phone));
    }

    @RequestMapping("/changePassword")
    public RetResult changePassword(String phone,String password){
        loginService.changePassword(phone, password);
        return RetResponse.makeOKRsp("密码修改成功");
    }
}
