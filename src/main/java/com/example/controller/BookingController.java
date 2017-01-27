package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by deadlock on 27/1/17.
 */
@RestController
public class BookingController {

    @GetMapping("/booking")
    public String getAllBookingStatus() {
        return "to be implemented";
    }
}
