package com.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("v1/api/producers")
public class KafkaProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class);
    }
    @GetMapping
    public String home(){
        return "kafka app";
    }
}
