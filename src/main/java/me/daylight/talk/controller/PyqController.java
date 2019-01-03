package me.daylight.talk.controller;

import me.daylight.talk.model.Pyq;
import me.daylight.talk.model.PyqComment;
import me.daylight.talk.ret.RetResponse;
import me.daylight.talk.ret.RetResult;
import me.daylight.talk.service.PyqCommentService;
import me.daylight.talk.service.PyqService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/pyq")
public class PyqController {
    @Resource
    private PyqService pyqService;

    @Resource
    private PyqCommentService pyqCommentService;

    @RequestMapping("/add")
    public RetResult addPyq(@RequestBody Pyq pyq){
        pyqService.insert(pyq);
        return RetResponse.makeOKRsp();
    }

    @RequestMapping("/delete")
    public RetResult<String> deletePyq(int id){
        pyqService.deletePyqById(id);
        return RetResponse.makeOKRsp("删除成功");
    }

    @RequestMapping("/queryMine")
    public RetResult<List<Pyq>> queryMyPyq(String phone,long mills){
        return RetResponse.makeOKRsp(pyqService.queryMyPyq(phone,new Timestamp(mills)));
    }

    @RequestMapping("/queryFriends")
    public RetResult<List<Pyq>> queryFriendPyq(String phone,long mills){
        return RetResponse.makeOKRsp(pyqService.queryFriendPyq(phone,new Timestamp(mills)));
    }

    @RequestMapping("/comment/add")
    public RetResult<String> addPyqComment(@RequestBody PyqComment pyqComment){
        pyqCommentService.insertComment(pyqComment);
        return RetResponse.makeOKRsp();
    }

    @RequestMapping("/comment/delete")
    public RetResult<String> deletePyqComment(int id){
        pyqCommentService.deleteComment(id);
        return RetResponse.makeOKRsp();
    }

    @RequestMapping("/comment/getById")
    public RetResult<PyqComment> getPyqComment(int id){
        return RetResponse.makeOKRsp(pyqCommentService.getComment(id));
    }

    @RequestMapping("/comment/queryByPyqId")
    public RetResult<List<PyqComment>> queryCommentsByPyqId(int pyqId){
        return RetResponse.makeOKRsp(pyqCommentService.queryComments(pyqId));
    }

    @RequestMapping("/comment/querySendByPhone")
    public RetResult<List<PyqComment>> querySendComment(String phone){
        return RetResponse.makeOKRsp(pyqCommentService.querySendComments(phone));
    }

    @RequestMapping("/comment/queryReceivedByPhone")
    public RetResult<List<PyqComment>> queryReceivesComment(String phone){
        return RetResponse.makeOKRsp(pyqCommentService.queryReceivedComments(phone));
    }
}
