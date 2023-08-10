package com.circulariot.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;

@Slf4j
@Service
public class ServerSentEventService {

    private FluxProcessor<Object, Object> processor;
    private Flux<Object> flux;

    public ServerSentEventService() {
        this.processor = DirectProcessor.create().serialize();

        this.flux = this.processor
                .doOnError(Throwable::printStackTrace)
                .publish()
                .autoConnect();
    }

    public Flux<Object> getEventFlux() {
        return flux;
    }

    public void sendEvent(String event) {
        processor.onNext(event);
    }
}
