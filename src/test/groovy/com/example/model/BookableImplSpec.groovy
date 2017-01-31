package com.example.model

import spock.lang.Specification
import spock.lang.Subject

/**
 * Created by deadlock on 27/1/17.
 */
class BookableImplSpec extends Specification {
    @Subject
    BookableImpl bookable

    Calendar time1100, time1230, time1200, time1300

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
    }

    def 'Can return bookable id and name'() {
        given:
        def item = new BookingItem(1, "meeting room")
        def calendar = new BookingCalendar()

        when:
        bookable = new BookableImpl(item, calendar)

        then:
        bookable.id()
        bookable.name()
    }

    def 'Can book 11:00 - 13:00'() {
        given:
        def item = new BookingItem(1, "meeting room")
        def calendar = new BookingCalendar()
        bookable = new BookableImpl(item, calendar)

        when:
        bookable.book(time1100, time1300)

        then:
        notThrown(ReservationFailedException)
    }

    def 'Should not allow to the same time slot twice'() {
        given:
        def item = new BookingItem(1, "meeting room")
        def calendar = new BookingCalendar()
        bookable = new BookableImpl(item, calendar)

        when:
        bookable.book(time1100, time1300)
        bookable.book(time1100, time1300)

        then:
        thrown(ReservationFailedException)
    }

    def 'Should not allow to book if time slot overlap'() {
        given:
        def item = new BookingItem(1, "meeting room")
        def calendar = new BookingCalendar()
        bookable = new BookableImpl(item, calendar)

        when:
        bookable.book(time1100, time1300)
        bookable.book(time1230, time1300)

        then:
        thrown(ReservationFailedException)
    }

    def 'Should allow to book if time slot does not overlap'() {
        given:
        def item = new BookingItem(1, "meeting room")
        def calendar = new BookingCalendar()
        bookable = new BookableImpl(item, calendar)

        when:
        bookable.book(time1100, time1230)
        bookable.book(time1230, time1300)

        then:
        notThrown(ReservationFailedException)
    }
}