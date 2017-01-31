package com.example.model

import spock.lang.Specification
import spock.lang.Subject


/**
 * Created by deadlock on 27/1/17.
 */
class BookingSpec extends Specification {
    @Subject
    BookableCollection booking

    Bookable meetingRoom, foosball

    void setup() {
        meetingRoom = BookableImpl.builder()
                .item(new BookingItem(1, "meeting room"))
                .calendar(new BookingCalendar())
                .build()
        foosball = BookableImpl.builder()
                .item(new BookingItem(2, "foosball"))
                .calendar(new BookingCalendar())
                .build()
    }


    def 'Should contains non null field bookables with no args'() {
        given:
        booking = new BookableCollection()

        expect:
        booking.count() == 0
    }

    def 'Should contains non null field bookables with list args'() {
        given:
        booking = new BookableCollection(Arrays.asList(meetingRoom, foosball))

        expect:
        booking.count() == 2
    }

    def 'Can add new bookable and return id'() {
        given:
        booking = new BookableCollection()

        when:
        def id = booking.add(meetingRoom)

        then:
        booking.count() == 1
        id == meetingRoom.id()
    }

    def 'Can get bookable by id'() {
        given:
        booking = new BookableCollection(Arrays.asList(meetingRoom, foosball))

        when:
        def optResult = booking.get(meetingRoom.id())

        then:
        optResult.isPresent()
        optResult.get() == meetingRoom
    }

    def 'Can delete bookable by id'() {
        given:
        booking = new BookableCollection(Arrays.asList(meetingRoom, foosball))

        when:
        booking.delete(meetingRoom.id())
        def optGetResult = booking.get(meetingRoom.id())

        then:
        booking.count() == 1
        !optGetResult.isPresent()
    }
}