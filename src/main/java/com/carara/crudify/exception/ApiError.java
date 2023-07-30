package com.carara.crudify.exception;

import lombok.Builder;

import java.util.List;

@Builder
public class ApiError {
    private int status;
    private String message;
    private List<String> details;

    public ApiError(int status, String message, List<String> details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

}
