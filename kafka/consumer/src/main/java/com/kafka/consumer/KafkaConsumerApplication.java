package com.kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("v1/api/consumer")
public class KafkaConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaConsumerApplication.class);
    }
    @GetMapping
    public String home(){
        return "kafka consumer application";
    }
}
