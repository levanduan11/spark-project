package com.spark.domainmodel;

import com.spark.domainmodel.enumeration.OrderType;

import java.time.LocalDateTime;

public record Order(
        Long id,
        Integer customerId,
        Integer productId,
        int productCount,
        LocalDateTime creationDate,
        OrderType type,
        int amount
) {
}
