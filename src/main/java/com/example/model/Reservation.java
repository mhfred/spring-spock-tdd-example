package com.example.model;

import lombok.Data;

import java.util.Calendar;

/**
 * Created by deadlock on 29/1/17.
 */
@Data
public class Reservation {
    private Calendar begin;
    private Calendar end;

    public Reservation(ReservationRequest reservationRequest) {
        this.begin = reservationRequest.getBegin();
        this.end = reservationRequest.getEnd();
    }
}
