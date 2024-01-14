package com.kafka.producer.service;

import com.google.common.base.Preconditions;
import com.spark.domainmodel.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.LinkedList;
import java.util.Map;
import java.util.function.Supplier;


import static com.spark.domainmodel.enumeration.OrderType.BUY;
import static com.spark.domainmodel.enumeration.OrderType.SELL;
import static java.time.LocalDateTime.now;
import static java.util.List.of;


@Configuration
@Slf4j
public class OrderService {
    private static long orderId = 0;
    private final Map<Integer, Integer> prices = Map.of(
            1, 1000,
            2, 2000,
            3, 3000,
            4, 4000,
            5, 5000,
            6, 6000,
            7, 7000,
            8, 8000,
            9, 9000,
            10, 10000);
    LinkedList<Order> buyOrders = new LinkedList<>(of(
            new Order(++orderId, 1, 1, 100, now(), BUY, 1000),
            new Order(++orderId, 2, 1, 200, now(), BUY, 1050),
            new Order(++orderId, 3, 1, 100, now(), BUY, 1030),
            new Order(++orderId, 4, 1, 200, now(), BUY, 1050),
            new Order(++orderId, 5, 1, 200, now(), BUY, 1000),
            new Order(++orderId, 11, 1, 100, now(), BUY, 1050)
    ));

    LinkedList<Order> sellOrders = new LinkedList<>(of(
            new Order(++orderId, 6, 1, 200, now(), SELL, 950),
            new Order(++orderId, 7, 1, 100, now(), SELL, 1000),
            new Order(++orderId, 8, 1, 100, now(), SELL, 1050),
            new Order(++orderId, 9, 1, 300, now(), SELL, 1000),
            new Order(++orderId, 10, 1, 200, now(), SELL, 1020)
    ));

    @Bean
    public Supplier<Message<Order>>orderBuySupplier(){
        return ()->{
            if (buyOrders.peek()!=null){
                var o = MessageBuilder
                        .withPayload(buyOrders.peek())
                        .setHeader(KafkaHeaders.KEY, Preconditions.checkNotNull(buyOrders.poll()).id())
                        .build();
                log.info("Order: {}",o.getPayload());
                return o;
            }else {
                return null;
            }
        };
    }
    @Bean
    public Supplier<Message<Order>>orderSellSupplier(){
        return ()->{
            if (sellOrders.peek()!=null){
                var o = MessageBuilder
                        .withPayload(sellOrders.peek())
                        .setHeader(KafkaHeaders.KEY,Preconditions.checkNotNull(sellOrders.poll()).id())
                        .build();
                log.info("Order: {}",o.getPayload());
                return o;
            }else {
                return null;
            }
        };
    }
}
