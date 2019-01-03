package me.daylight.talk.service.impl;

import me.daylight.talk.mapper.PyqCommentMapper;
import me.daylight.talk.model.PyqComment;
import me.daylight.talk.service.PyqCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("pyqCommentService")
public class PyqCommentServiceImpl implements PyqCommentService {
    @Resource
    private PyqCommentMapper pyqCommentMapper;

    @Override
    public void deleteComment(int id) {
        pyqCommentMapper.deleteById(id);
    }

    @Override
    public void insertComment(PyqComment pyqComment) {
        pyqCommentMapper.insert(pyqComment);
    }

    @Override
    public PyqComment getComment(int id) {
        return pyqCommentMapper.selectById(id);
    }

    @Override
    public List<PyqComment> queryComments(int pyqId) {
        return pyqCommentMapper.queryCommentByPyqId(pyqId);
    }

    @Override
    public List<PyqComment> queryReceivedComments(String phone) {
        return pyqCommentMapper.queryReceivedComment(phone);
    }

    @Override
    public List<PyqComment> querySendComments(String phone) {
        return pyqCommentMapper.querySendComment(phone);
    }
}
