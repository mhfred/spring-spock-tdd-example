package com.example.model;

/**
 * Created by deadlock on 29/1/17.
 */
public class ReservationFailedException extends RuntimeException {
    public ReservationFailedException() {
    }

    public ReservationFailedException(String message) {
        super(message);
    }

    public ReservationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReservationFailedException(Throwable cause) {
        super(cause);
    }

    public ReservationFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
