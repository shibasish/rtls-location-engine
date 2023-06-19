package com.circulariot.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/tags").withSockJS();
    }

//    @Bean
//    public WebSocketHandlerAdapter handlerAdapter() {
//        return new WebSocketHandlerAdapter();
//    }
//
//    @Bean
//    public HandlerMapping webSocketHandlerMapping(WebSocketHandler webSocketHandler) {
//        Map<String, WebSocketHandler> map = new HashMap<>();
//        map.put("/", webSocketHandler);
//
//        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
//        handlerMapping.setOrder(1);
//        handlerMapping.setUrlMap(map);
//        return handlerMapping;
//    }

//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/tags").withSockJS();
//    }
}
