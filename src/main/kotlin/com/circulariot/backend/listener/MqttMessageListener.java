package com.circulariot.backend.listener;


import jakarta.websocket.MessageHandler;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@MessageEndpoint
@Component
public class MqttMessageListener implements MessageHandler {

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<String> message) {
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);
    }
}
