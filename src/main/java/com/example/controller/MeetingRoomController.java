package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by deadlock on 27/1/17.
 */
@RestController
public class MeetingRoomController {

    @GetMapping("/meeting_room/1/booking")
    public String getMeetingRoomBooking() {
        return "to to completed";
    }
}
