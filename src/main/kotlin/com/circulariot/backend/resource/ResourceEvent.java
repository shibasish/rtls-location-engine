package com.circulariot.backend.resource;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ResourceEvent {

    private final EmitterProcessor<String> streamProcessor = EmitterProcessor.create();

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getSee() {
        return this.streamProcessor;
    }

    @Bean
    public Consumer<Flux<String>> receiveSse() {
        return recordFlux ->
                recordFlux
                        .doOnNext(this.streamProcessor::onNext)
                        .doOnNext(value -> log.info("*" +value))
                        .subscribe();
    }
}
