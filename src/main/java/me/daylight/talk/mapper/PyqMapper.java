package me.daylight.talk.mapper;

import me.daylight.talk.model.Pyq;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface PyqMapper {
    void deleteById(Integer id);

    void insert(Pyq record);

    Pyq selectById(Integer id);

    List<Pyq> queryMyPyq(@Param("phone")String phone, @Param("time")Timestamp time);

    List<Pyq> queryFriendPyq(@Param("phone")String phone, @Param("time")Timestamp time);
}