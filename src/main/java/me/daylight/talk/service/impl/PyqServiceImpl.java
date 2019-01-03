package me.daylight.talk.service.impl;

import me.daylight.talk.mapper.PyqMapper;
import me.daylight.talk.model.Pyq;
import me.daylight.talk.service.PyqService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service("pyqService")
public class PyqServiceImpl implements PyqService {
    @Resource
    private PyqMapper pyqMapper;

    @Override
    public void insert(Pyq pyq) {
        pyqMapper.insert(pyq);
    }

    @Override
    public void deletePyqById(int id) {
        pyqMapper.deleteById(id);
    }

    @Override
    public List<Pyq> queryMyPyq(String phone, Timestamp time) {
        return pyqMapper.queryMyPyq(phone,time);
    }

    @Override
    public List<Pyq> queryFriendPyq(String phone, Timestamp time) {
        return pyqMapper.queryFriendPyq(phone,time);
    }
}
