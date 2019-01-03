package me.daylight.talk.service;

import me.daylight.talk.model.Pyq;

import java.sql.Timestamp;
import java.util.List;

public interface PyqService {
    void insert(Pyq pyq);

    void deletePyqById(int id);

    List<Pyq> queryMyPyq(String phone, Timestamp time);

    List<Pyq> queryFriendPyq(String phone, Timestamp time);
}
