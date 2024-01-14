package com.spark.domainmodel;

import java.time.LocalDateTime;

public record Transaction(
        Long id,
        Long buyOrderId,
        Long sellOrderId,
        int amount,
        int price,
        LocalDateTime creationTime
) {
}
