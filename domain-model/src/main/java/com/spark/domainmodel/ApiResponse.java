package com.spark.domainmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Value
public class ApiResponse<DATA> {
    DATA data;
    String message;
    String status;
    String code;
    Integer page;
    Integer size;
    Integer totalPages;
    Integer totalElements;
}
