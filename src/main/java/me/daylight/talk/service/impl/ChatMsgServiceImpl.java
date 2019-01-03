package me.daylight.talk.service.impl;

import me.daylight.talk.mapper.ChatMessageMapper;
import me.daylight.talk.model.ChatMessage;
import me.daylight.talk.service.ChatMsgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("chatMsgService")
public class ChatMsgServiceImpl implements ChatMsgService {
    @Resource
    private ChatMessageMapper chatMessageMapper;

    @Override
    public void insert(ChatMessage message) {
        chatMessageMapper.insert(message);
    }

    @Override
    public void deleteById(int id) {
        chatMessageMapper.deleteById(id);
    }

    @Override
    public List<ChatMessage> queryMessage(String sendPhone, String receivePhone) {
        return chatMessageMapper.queryMessageByPhone(sendPhone, receivePhone);
    }
}
