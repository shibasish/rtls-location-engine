package com.circulariot.backend.resource;


import lombok.RequiredArgsConstructor;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Controller
@RequiredArgsConstructor
public class ResourceEvent {

//    private final SensorService service;
    private final PublishSubscribeChannel mqttOutputChannel;

//    @GetMapping("/")
//    public Flux<Message> handle() {
//        return service.getInputChannelFlux();
//    }

    @CrossOrigin
    @MessageMapping("/tags")
    @SendTo("/topic/details")
    public Flux<Message> handle() {
        System.out.println("hello");
        BlockingQueue<Message<?>> messageQueue = new LinkedBlockingQueue<>();

        return Flux.create(fluxSink -> {
            messageQueue.clear();

            MessageHandler messageHandler = message ->  {
                System.out.println("message received: "+ message.getPayload());
                messageQueue.offer(message);
                fluxSink.next(message);
            };

            mqttOutputChannel.subscribe(messageHandler);
        }, FluxSink.OverflowStrategy.BUFFER);
    }
//
//    public void processMessage(String message) {
//        // Process the incoming WebSocket message
//        System.out.println("Received message: " + message);
//
//        // Broadcast the message to all WebSocket sessions
//        messageSink.next(message);
//    }
//
//    private void cleanup(WebSocketSession session) {
//        // Cleanup resources when the WebSocket session is closed
//    }
}
