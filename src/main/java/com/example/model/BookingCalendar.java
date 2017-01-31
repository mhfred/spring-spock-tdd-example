package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by deadlock on 27/1/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCalendar {
    private List<Reservation> reservations = new ArrayList<>();

    public Reservation addReservation(ReservationRequest reservationRequest) throws ReservationFailedException {
        if (isAvailable(reservationRequest)) {
            Reservation reservation = new Reservation(reservationRequest);
            reservations.add(reservation);
            return reservation;
        } else {
            throw new ReservationFailedException("requested time is not available.");
        }
    }

    public Integer count() {
        return reservations.size();
    }

    public boolean isAvailable(ReservationRequest request) {
        return reservations.stream()
                .filter(reservation -> isOverlap(reservation, request) | isTheSame(reservation, request))
                .count() == 0;
    }

    private boolean isOverlap(Reservation reservation, ReservationRequest request) {
        return isInBetween(reservation.getBegin(), reservation.getEnd(), request.getBegin())
                | isInBetween(reservation.getBegin(), reservation.getEnd(), request.getEnd())
                | isInBetween(request.getBegin(), request.getEnd(), reservation.getBegin())
                | isInBetween(request.getBegin(), request.getEnd(), reservation.getEnd());
    }

    private boolean isInBetween(Calendar begin, Calendar end, Calendar calendar) {
        boolean isAfterBegin = begin.compareTo(calendar) < 0;
        boolean isBeforeEnd = calendar.compareTo(end) < 0;
        return isAfterBegin & isBeforeEnd;
    }

    private boolean isTheSame(Reservation reservation, ReservationRequest reservationRequest) {
        return reservation.getBegin().compareTo(reservationRequest.getBegin()) == 0
                & reservation.getEnd().compareTo(reservationRequest.getEnd()) == 0;
    }
}
