package me.daylight.talk.mapper;

import me.daylight.talk.model.PyqComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PyqCommentMapper {
    int deleteById(Integer id);

    int insert(PyqComment record);

    PyqComment selectById(Integer id);

    List<PyqComment> queryCommentByPyqId(@Param("pyqId") Integer id);

    List<PyqComment> queryReceivedComment(@Param("phone") String phone);

    List<PyqComment> querySendComment(@Param("phone") String phone);
}