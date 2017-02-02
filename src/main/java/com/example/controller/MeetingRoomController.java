package com.example.controller;

import com.example.controller.pojo.GenericResponse;
import com.example.controller.request.MeetingRoomBookingRequest;
import com.example.service.BookingService;
import com.example.service.dto.BookingRecord;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by deadlock on 27/1/17.
 */
@RestController
@AllArgsConstructor
public class MeetingRoomController {

    private BookingService bookingService;

    @GetMapping("/meeting_room/booking")
    public GenericResponse<List<BookingRecord>> getMeetingRoomBookingRecords() {
        GenericResponse<List<BookingRecord>> response = new GenericResponse<>();
        List<BookingRecord> bookingRecords = bookingService.meetingRoomBookingSummary();
        response.setData(bookingRecords);
        return response;
    }

    @PostMapping("/meeting_room/booking")
    public GenericResponse book(@RequestBody MeetingRoomBookingRequest request) {
        GenericResponse response = new GenericResponse();
        bookingService.bookMeetingRoom(request);
        return response;
    }
}
