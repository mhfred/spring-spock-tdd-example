package com.example.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;

/**
 * Created by deadlock on 31/1/17.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRoomBookingRequest {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Calendar begin;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Calendar end;
}
