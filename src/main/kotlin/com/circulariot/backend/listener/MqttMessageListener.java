package com.circulariot.backend.listener;


import jakarta.websocket.MessageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.Message;

@MessageEndpoint
@RequiredArgsConstructor
public class MqttMessageListener implements MessageHandler {

    private final PublishSubscribeChannel mqttOutputChannel;

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handleMessage(Message<String> message) {
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);
        mqttOutputChannel.send(message);
    }
}
