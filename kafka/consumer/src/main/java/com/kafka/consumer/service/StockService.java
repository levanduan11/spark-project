package com.kafka.consumer.service;

import com.spark.domainmodel.Order;
import com.spark.domainmodel.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.StreamJoined;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.time.Duration;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

@Configuration
@Slf4j
public class StockService {

    @Bean
    public BiConsumer<KStream<Long, Order>, KStream<Long, Order>> orders() {
        return (orderBuy, orderSell) -> orderBuy
                .merge(orderSell)
                .peek((k, v) -> {
                    log.info("New({}): {}", k, v);
                });
    }

    @Bean
    public BiFunction<KStream<Long, Order>, KStream<Long, Order>, KStream<Long, Transaction>> transactions() {
        return (orderBuy, orderSell) -> orderBuy
                .selectKey((Long k, Order v) -> v.productId())
                .join(orderSell.selectKey((k, v) -> v.productId()),
                        this::execute,
                        JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofSeconds(10)),
                        StreamJoined.with(Serdes.Integer(), new JsonSerde<>(Order.class), new JsonSerde<>(Order.class)))
                .filterNot((k, v) -> v == null)
                .map((k, v) -> new KeyValue<>(v.id(), v))
                .peek((k, v) -> log.info("Done ->{}", v));
    }

    private Transaction execute(Order orderBuy, Order orderSell) {
        // TODO
        return null;
    }
}
