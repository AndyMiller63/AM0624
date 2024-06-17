package com.windmill.rentalservice.exception;

public class RentalCreationException extends RuntimeException {
    public RentalCreationException(String message) {
        super(message);
    }

    public RentalCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
