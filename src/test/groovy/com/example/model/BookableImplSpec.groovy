package com.example.model

import spock.lang.Specification
import spock.lang.Subject


/**
 * Created by deadlock on 27/1/17.
 */
class BookableImplSpec extends Specification {
    @Subject
    BookableImpl bookable

    def 'Should return booking id and name'() {
        given:
        def item = new BookingItem(1, "meeting room")
        def calendar = new BookingCalendar()

        when:
        bookable = new BookableImpl(item, calendar)

        then:
        bookable.id()
        bookable.name()
    }
}