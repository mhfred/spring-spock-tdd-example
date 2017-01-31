package com.example.service

import com.example.model.*
import com.example.service.dto.BookingRecord
import com.example.util.CalendarUtil
import spock.lang.Specification
import spock.lang.Subject

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.everyItem
import static org.hamcrest.Matchers.isA


/**
 * Created by deadlock on 28/1/17.
 */
class BookingServiceSpec extends Specification {
    @Subject
    BookingService bookingService

    BookableImplFactory bookableImplFactory = Mock(BookableImplFactory)

    Bookable meetingRoomBookable, foosballBookable
    BookingItem meetingRoom, foosball

    void setup() {
        meetingRoom = BookingItem.builder().id(1).name("meeting room").build()
        foosball = BookingItem.builder().id(2).name("foosball table").build()

        meetingRoomBookable = BookableImpl.builder()
                .item(meetingRoom)
                .calendar(new BookingCalendar())
                .build()
        foosballBookable = BookableImpl.builder()
                .item(foosball)
                .calendar(new BookingCalendar())
                .build()
    }

    def 'Can get optional bookable by id'() {
        given:
        bookableImplFactory.createFromBookingItems() >> [meetingRoomBookable, foosballBookable]
        bookingService = new BookingService(bookableImplFactory)

        when:
        def optBookable = bookingService.getBookable(1)

        then:
        optBookable.isPresent()
        optBookable.get().id() == 1
        optBookable.get().name() == "meeting room"
    }

    def 'Can request reservation for meeting room'() {
        given:
        bookableImplFactory.createFromBookingItems() >> [meetingRoomBookable, foosballBookable]
        bookingService = new BookingService(bookableImplFactory)
        Calendar time1200 = CalendarUtil.time1200
        Calendar time1230 = CalendarUtil.time1230
        when:
        bookingService.bookMeetingRoom(time1200, time1230)

        then:
        notThrown(ReservationFailedException)
    }

    def 'Can display booking records for a given bookable'() {
        given:
        bookableImplFactory.createFromBookingItems() >> [meetingRoomBookable, foosballBookable]
        bookingService = new BookingService(bookableImplFactory)
        Calendar time1200 = CalendarUtil.time1200
        Calendar time1230 = CalendarUtil.time1230
        bookingService.bookMeetingRoom(time1200, time1230)

        when:
        List<BookingRecord> bookingSummary = bookingService.meetingRoomBookingSummary()

        then:
        bookingSummary
        and: 'is a list of booking record'
        assertThat bookingSummary, everyItem(isA(BookingRecord.class))
        and: 'contains correct number of booking records'
        bookingSummary.size() == 1
    }

    def 'Booking time is correct after success booking'() {
        given:
        bookableImplFactory.createFromBookingItems() >> [meetingRoomBookable, foosballBookable]
        bookingService = new BookingService(bookableImplFactory)
        Calendar time1200 = CalendarUtil.time1200
        Calendar time1230 = CalendarUtil.time1230

        when:
        bookingService.bookMeetingRoom(time1200, time1230)
        List<BookingRecord> bookingSummary = bookingService.meetingRoomBookingSummary()
        BookingRecord bookingRecord = bookingSummary.get(0)

        then:
        bookingRecord.getBegin() == CalendarUtil.time1200
        bookingRecord.getEnd() == CalendarUtil.time1230
    }
}