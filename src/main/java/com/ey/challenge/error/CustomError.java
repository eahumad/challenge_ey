package com.ey.challenge.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomError {
    private String message;

    public CustomError(String message) {
        this.message = message;
    }

    @JsonProperty("mensaje")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
