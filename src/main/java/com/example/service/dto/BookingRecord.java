package com.example.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

/**
 * Created by deadlock on 31/1/17.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRecord {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Singapore")
    private Calendar begin;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Singapore")
    private Calendar end;
}
