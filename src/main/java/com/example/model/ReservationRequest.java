package com.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.Calendar;

/**
 * Created by deadlock on 29/1/17.
 */
@Builder
@Data
public class ReservationRequest {
    private Calendar begin;
    private Calendar end;
}
