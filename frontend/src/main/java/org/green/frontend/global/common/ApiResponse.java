package org.green.frontend.global.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private ApiStatus status;
    private T body;
    private LocalDateTime timestamp;

    public ApiResponse(ApiStatus status, T body) {
        this.status = status;
        this.body = body;
        this.timestamp = LocalDateTime.now();
    }

    public enum ApiStatus {
        SUCCESS,
        ERROR
    }
}
