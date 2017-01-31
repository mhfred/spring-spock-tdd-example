package com.example.service.dto;

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
    private Calendar begin;
    private Calendar end;
}
