package me.daylight.talk.service;

import me.daylight.talk.model.ChatMessage;

import java.util.List;

public interface ChatMsgService {
    void insert(ChatMessage message);

    void deleteById(int id);

    List<ChatMessage> queryMessage(String sendPhone, String receivePhone);
}
