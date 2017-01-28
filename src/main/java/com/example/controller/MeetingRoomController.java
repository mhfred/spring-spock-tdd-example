package com.example.controller;

import com.example.controller.pojo.GenericResponse;
import com.example.controller.response.ItemBookingInfo;
import com.example.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * Created by deadlock on 27/1/17.
 */
@RestController
@AllArgsConstructor
public class MeetingRoomController {

    private BookingService bookingService;

    @GetMapping("/meeting_room/{id}/booking")
    public GenericResponse getMeetingRoomBooking(@PathVariable("id") Long id) {
        GenericResponse<List<ItemBookingInfo>> response = new GenericResponse<>();
        List<ItemBookingInfo> itemBookingInfoList = Arrays.asList(
                ItemBookingInfo.builder()
                        .id(1)
                        .date(Date.from(Instant.now()))
                        .reservedSlots(Arrays.asList(1, 2, 3))
                        .build()
        );
        response.setData(itemBookingInfoList);
        return response;
    }
}
