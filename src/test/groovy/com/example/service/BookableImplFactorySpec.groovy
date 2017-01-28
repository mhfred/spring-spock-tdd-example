package com.example.service

import com.example.model.BookingItem
import spock.lang.Specification
import spock.lang.Subject


/**
 * Created by deadlock on 28/1/17.
 */
class BookableImplFactorySpec extends Specification {
    @Subject
    BookableImplFactory bookableImplFactory

    BookingItemFactory bookingItemFactory = Mock(BookingItemFactory)

    BookingItem meetingRoom, foosball

    void setup() {
        meetingRoom = BookingItem.builder().id(1).name("meeting room").build()
        foosball = BookingItem.builder().id(2).name("foosball table").build()
    }

    def 'Can create bookables from booking items'() {
        given:
        bookableImplFactory = new BookableImplFactory(bookingItemFactory)
        bookingItemFactory.createFromEntity() >> [meetingRoom, foosball]

        when:
        def bookableList = bookableImplFactory.createFromBookingItems()

        then:
        bookableList
        bookableList.size() == 2
    }
}