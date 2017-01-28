package com.example.service

import com.example.persist.entity.BookingItem
import com.example.persist.repository.BookingItemRepository
import spock.lang.Specification
import spock.lang.Subject

/**
 * Created by deadlock on 28/1/17.
 */
class BookingItemFactoryTest extends Specification {
    @Subject
    BookingItemFactory bookingItemFactory

    BookingItemRepository bookingItemRepository = Mock(BookingItemRepository)

    BookingItem meetingRoom, foosball

    void setup() {
        meetingRoom = BookingItem.builder().id(1).name("meeting room").build()
        foosball = BookingItem.builder().id(2).name("foosball table").build()
    }

    def 'can create bookable item list from booking item repository'() {
        given:
        bookingItemRepository.findAll() >> [meetingRoom, foosball]
        bookingItemFactory = new BookingItemFactory(bookingItemRepository)

        when:
        def bookingItemList = bookingItemFactory.createFromEntity()

        then:
        bookingItemList
        bookingItemList.size() == 2
    }
}
