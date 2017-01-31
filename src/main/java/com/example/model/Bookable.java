package com.example.model;

import java.util.Calendar;
import java.util.List;

/**
 * Created by deadlock on 27/1/17.
 */
public interface Bookable {
    Integer id();

    String name();

    void book(Calendar begin, Calendar end) throws ReservationFailedException;

    List<Reservation> getReservations();
}
