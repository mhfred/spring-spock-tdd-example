package com.example.model

import spock.lang.Specification
import spock.lang.Subject


/**
 * Created by deadlock on 29/1/17.
 */
class BookingCalendarSpec extends Specification {
    @Subject
    BookingCalendar bookingCalendar

    Calendar time1100, time1230, time1200, time1300
    ReservationRequest request1100To1230, request1200To1300, request1100To1300, request1200To1230, request1230To1300

    void setup() {
        time1100 = Calendar.getInstance()
        time1100.set(Calendar.HOUR_OF_DAY, 11)
        time1100.set(Calendar.MINUTE, 0)
        time1100.set(Calendar.SECOND, 0)
        time1100.set(Calendar.MILLISECOND, 0)

        time1230 = Calendar.getInstance()
        time1230.set(Calendar.HOUR_OF_DAY, 12)
        time1230.set(Calendar.MINUTE, 30)
        time1230.set(Calendar.SECOND, 0)
        time1230.set(Calendar.MILLISECOND, 0)

        time1200 = Calendar.getInstance()
        time1200.set(Calendar.HOUR_OF_DAY, 12)
        time1200.set(Calendar.MINUTE, 00)
        time1200.set(Calendar.SECOND, 0)
        time1200.set(Calendar.MILLISECOND, 0)

        time1300 = Calendar.getInstance()
        time1300.set(Calendar.HOUR_OF_DAY, 13)
        time1300.set(Calendar.MINUTE, 00)
        time1300.set(Calendar.SECOND, 0)
        time1300.set(Calendar.MILLISECOND, 0)

        request1100To1230 = ReservationRequest.builder()
                .begin(time1100)
                .end(time1230)
                .build()

        request1200To1300 = ReservationRequest.builder()
                .begin(time1200)
                .end(time1300)
                .build()

        request1100To1300 = ReservationRequest.builder()
                .begin(time1100)
                .end(time1300)
                .build()

        request1200To1230 = ReservationRequest.builder()
                .begin(time1200)
                .end(time1230)
                .build()

        request1230To1300 = ReservationRequest.builder()
                .begin(time1230)
                .end(time1300)
                .build()
    }

    def 'can add reservation when no existing reservation'() {
        given:
        bookingCalendar = new BookingCalendar()

        when:
        bookingCalendar.addReservation(request1100To1230)

        then:
        bookingCalendar.count() == 1
    }

    def 'Should throw exception when reservation requested twice'() {
        given:
        bookingCalendar = new BookingCalendar()
        bookingCalendar.addReservation(request1100To1230)

        when:
        bookingCalendar.addReservation(request1100To1230)

        then:
        thrown(ReservationFailedException)
    }

    def 'Should throw exception when try to reserve 12:00 - 13:00 given 11:00 - 12:30 reserved'() {
        given:
        bookingCalendar = new BookingCalendar()
        bookingCalendar.addReservation(request1100To1230)

        when:
        bookingCalendar.addReservation(request1200To1300)

        then:
        thrown(ReservationFailedException)
    }

    def 'Should throw exception when try to reserve 11:00 - 13:00 given 12:00 - 12:30 reserved'() {
        given:
        bookingCalendar = new BookingCalendar()
        bookingCalendar.addReservation(request1100To1300)

        when:
        bookingCalendar.addReservation(request1200To1230)

        then:
        thrown(ReservationFailedException)
    }

    def 'Should throw exception when try to reserve 12:00 - 12:30 given 11:00 - 13:00 reserved'() {
        given:
        bookingCalendar = new BookingCalendar()
        bookingCalendar.addReservation(request1200To1230)

        when:
        bookingCalendar.addReservation(request1100To1300)

        then:
        thrown(ReservationFailedException)
    }

    def 'Should throw exception when try to reserve 12:00 - 12:30 given 11:00 - 12:30 reserved'() {
        given:
        bookingCalendar = new BookingCalendar()
        bookingCalendar.addReservation(request1100To1230)

        when:
        bookingCalendar.addReservation(request1200To1230)

        then:
        thrown(ReservationFailedException)
    }

    def 'Should not throw exception when try to reserve 12:30 - 13:00 given 12:00 - 12:30 reserved'() {
        given:
        bookingCalendar = new BookingCalendar()
        bookingCalendar.addReservation(request1200To1230)

        when:
        bookingCalendar.addReservation(request1230To1300)

        then:
        notThrown(ReservationFailedException)
    }
}