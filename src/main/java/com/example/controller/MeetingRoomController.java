package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by deadlock on 27/1/17.
 */
@RestController
public class MeetingRoomController {

    @GetMapping("/meeting_room/{id}/booking")
    public String getMeetingRoomBooking(@PathVariable("id") Long id) {
        return "to to completed";
    }
}
