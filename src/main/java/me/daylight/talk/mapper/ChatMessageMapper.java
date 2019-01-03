package me.daylight.talk.mapper;

import me.daylight.talk.model.ChatMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChatMessageMapper {
    void deleteById(Integer id);

    void insert(ChatMessage record);

    ChatMessage selectById(Integer id);

    List<ChatMessage> queryMessageByPhone(@Param("sendPhone")String sendPhone,@Param("receivePhone")String receivePhone);
}