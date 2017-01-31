package com.example.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;

import java.util.Calendar;
import java.util.List;

/**
 * Created by deadlock on 27/1/17.
 */
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookableImpl implements Bookable {
    @NonNull
    private BookingItem item;
    @NonNull
    private BookingCalendar calendar;

    @Override
    public Integer id() {
        return item.getId();
    }

    @Override
    public String name() {
        return item.getName();
    }

    @Override
    public void book(Calendar begin, Calendar end) throws ReservationFailedException {
        ReservationRequest request = ReservationRequest.builder()
                .begin(begin)
                .end(end)
                .build();
        calendar.addReservation(request);
    }

    @Override
    public List<Reservation> getReservations() {
        return calendar.getReservations();
    }
}
