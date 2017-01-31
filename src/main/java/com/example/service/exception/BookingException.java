package com.example.service.exception;

/**
 * Created by deadlock on 30/1/17.
 */
public class BookingException extends RuntimeException {
    public BookingException() {
    }

    public BookingException(String message) {
        super(message);
    }

    public BookingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingException(Throwable cause) {
        super(cause);
    }

    public BookingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
