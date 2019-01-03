package me.daylight.talk.service;

import me.daylight.talk.model.PyqComment;

import java.util.List;

public interface PyqCommentService {
    void deleteComment(int id);

    void insertComment(PyqComment pyqComment);

    PyqComment getComment(int id);

    List<PyqComment> queryComments(int pyqId);

    List<PyqComment> queryReceivedComments(String phone);

    List<PyqComment> querySendComments(String phone);
}
