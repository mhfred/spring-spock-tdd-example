package com.example.model

import spock.lang.Specification
import spock.lang.Subject


/**
 * Created by deadlock on 27/1/17.
 */
class BookingSpec extends Specification {
    @Subject
    Booking booking

    Bookable a, b, c

    void setup() {
        a = BookableImpl.builder()
                .item(new BookingItem())
                .calendar(new BookingCalendar())
                .build()
        b = BookableImpl.builder()
                .item(new BookingItem())
                .calendar(new BookingCalendar())
                .build()
        c = BookableImpl.builder()
                .item(new BookingItem())
                .calendar(new BookingCalendar())
                .build()
    }


    def 'Should contains non null field bookables with no args'() {
        given:
        booking = new Booking()

        expect:
        booking.count() == 0
    }

    def 'Should contains non null field bookables with list args'() {
        given:
        booking = new Booking(Arrays.asList(a, b, c))

        expect:
        booking.count() == 3
    }

    def 'Can add new bookable and return id'() {
        given:
        booking = new Booking()

        when:
        def id = booking.add(a)

        then:
        booking.count() == 1
        id == a.id()
    }

    def 'Can get bookable by id'() {
        given:
        booking = new Booking(Arrays.asList(a, b, c))

        when:
        def optResult = booking.get(a.id())

        then:
        optResult.isPresent()
        optResult.get() == a
    }

    def 'Can delete bookable by id'() {
        given:
        booking = new Booking(Arrays.asList(a, b, c))

        when:
        booking.delete(a.id())
        def optGetResult = booking.get(a.id())

        then:
        booking.count() == 2
        !optGetResult.isPresent()
    }
}