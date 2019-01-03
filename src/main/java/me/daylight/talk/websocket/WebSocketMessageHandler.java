package me.daylight.talk.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.daylight.talk.model.ChatMessage;
import me.daylight.talk.service.ChatMsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketMessageHandler implements WebSocketHandler {
    @Resource
    private ChatMsgService chatMsgService;
    private static final int WebSocket_Type_Message=101;
    private static final int WebSocket_Type_HeartBeat=102;
    private static final int WebSocket_Type_Push=103;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    //保存所有连接上的session
    private static Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    //保存离线消息
    private static Map<String,LinkedList<String>> offlineMsg=new ConcurrentHashMap<>();
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        String phone=(String)webSocketSession.getAttributes().get("phone");
        sessionMap.put(phone,webSocketSession);

        logger.info("【" + phone + "】连接上服务器");
        if (offlineMsg.containsKey(phone)) {
            while (!offlineMsg.get(phone).isEmpty())
                sendMsg(webSocketSession, offlineMsg.get(phone).poll());
            offlineMsg.remove(phone);
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        String phone=(String)webSocketSession.getAttributes().get("phone");
        Object message=webSocketMessage.getPayload();

        if (message instanceof String) {
            Gson gson=new Gson();
            JsonObject jsonObject=gson.fromJson((String)message, JsonObject.class);
            int msgType=jsonObject.get("msgType").getAsInt();
            switch (msgType){
                case WebSocket_Type_Message:
                    String content=jsonObject.get("msg").getAsString();
                    logger.info("【" + phone + "】客户端的发送消息======内容[" + message + "]");
                    ChatMessage chatMessage=gson.fromJson(content,ChatMessage.class);
                    chatMsgService.insert(chatMessage);
                    String receivePhone = chatMessage.getReceivePhone();
                    if (sessionMap.containsKey(receivePhone))
                        sendMsg(sessionMap.get(receivePhone), (String) message);
                    else {
                        if (offlineMsg.containsKey(receivePhone))
                            offlineMsg.get(receivePhone).add((String) message);
                        else {
                            LinkedList<String> msg = new LinkedList<>();
                            msg.add((String) message);
                            offlineMsg.put(receivePhone, msg);
                        }
                    }
                    break;
                case WebSocket_Type_HeartBeat:
                    webSocketSession.sendMessage(new TextMessage((String)message));
                    break;
                case WebSocket_Type_Push:
                    String pushTo=jsonObject.get("phone").getAsString();
                    if (sessionMap.containsKey(pushTo))
                        sendMsg(sessionMap.get(pushTo), (String) message);
                    break;
            }
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) {
        String phone=(String) webSocketSession.getAttributes().get("phone");
        sessionMap.remove(phone);
        logger.error("【" +phone + "】退出了,"+throwable.toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus){
        String phone=(String) webSocketSession.getAttributes().get("phone");
        sessionMap.remove(phone);
        logger.info("【" + phone + "】退出了");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    private synchronized void sendMsg(WebSocketSession session, String msg) throws IOException {
        session.sendMessage(new TextMessage(msg));
    }
}
