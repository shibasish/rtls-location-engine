package com.circulariot.backend.service;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//@Service
//@MessageEndpoint
//@RequiredArgsConstructor
public class SensorService<T> {

//    private final PublishSubscribeChannel mqttOutputChannel;

//    @ServiceActivator(inputChannel = "mqttOutputChannel")
//    public Flux<Message<?>> getInputChannelFlux(Message message) {
////        return Flux.create(emitter -> {
////            MessageHandler handler =  message -> {
////                System.out.println("Flux: "+ message.getPayload());
////                emitter.next(message);
////            };
////            mqttOutputChannel.subscribe(handler);
////        });
//
//        Flux<Message<?>> flux = convertToFlux(mqttOutputChannel);
//
//        return flux;
//    }

    private static Flux<Message<?>> convertToFlux(SubscribableChannel subscribableChannel) {

        subscribableChannel.subscribe(message -> System.out.println("received: " + message.getPayload()));

        BlockingQueue<Message<?>> messageQueue = new LinkedBlockingQueue<>();

        return Flux.create(fluxSink -> {
            messageQueue.clear();

            MessageHandler messageHandler = message ->  {
                System.out.println("message received: "+ message.getPayload());
                messageQueue.offer(message);
                fluxSink.next(message);
            };

            subscribableChannel.subscribe(messageHandler);
        }, FluxSink.OverflowStrategy.BUFFER);
    }
}
