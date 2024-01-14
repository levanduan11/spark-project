package com.kafka.orderservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
@Slf4j
public class ProduceEvents {

    private final Sinks.Many<String>data;

    public ProduceEvents() {
        this.data = Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<String>> stringSupplier() {
        return () -> Flux.fromStream(Stream.generate(() -> {
            try {
                Thread.sleep(5000);
                log.info("Sending message ==========");
                return "Hello from Supplier";
            } catch (InterruptedException e) {
                return "Exception Occurred";
            }
        })).subscribeOn(Schedulers.boundedElastic()).share();
    }
    @Bean
    public Consumer<Flux<String>>consumeString(){
        return stringFlux ->
                stringFlux.doOnNext(
                        s -> {
                            log.info("Consumed message: {}",s);
                            data.tryEmitNext(s);
                        }).subscribe();
    }

   public Flux<String>data(){
        return data.asFlux();
   }
}
