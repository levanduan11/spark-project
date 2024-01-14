package com.kafka.orderservice.controller;

import com.kafka.orderservice.service.ProduceEvents;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("v1/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final InteractiveQueryService queryService;
    private final ProduceEvents produceEvents;

    @GetMapping(path = "/",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<List<String>> getAll() {
        return ResponseEntity.ok(produceEvents.data().toStream().toList());
    }
}
