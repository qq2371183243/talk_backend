package me.daylight.talk.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

    @Bean
    public WebSocketMessageHandler webSocketMessageHandler(){
        return new WebSocketMessageHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(webSocketMessageHandler(),"/webSocketService")
                .addInterceptors(new MessageWebSocketInterceptor()).setAllowedOrigins("*");
    }
}
