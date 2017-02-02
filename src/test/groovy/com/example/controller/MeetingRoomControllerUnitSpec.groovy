package com.example.controller

import com.example.controller.request.MeetingRoomBookingRequest
import com.example.service.BookingService
import com.example.service.dto.BookingRecord
import com.example.util.CalendarUtil
import spock.lang.Specification
import spock.lang.Subject


/**
 * Created by deadlock on 27/1/17.
 */
class MeetingRoomControllerUnitSpec extends Specification {

    @Subject
    MeetingRoomController controller

    BookingService bookingService

    def 'Injection is succeed'() {
        given:
        bookingService = Mock(BookingService)
        controller = new MeetingRoomController(bookingService)

        expect: true
    }

    def 'can get correct meeting room booking info'() {
        given:
        BookingRecord today1100To1200 = BookingRecord.builder()
                .begin(CalendarUtil.time1100)
                .end(CalendarUtil.time1200)
                .build()
        bookingService = Mock(BookingService)
        bookingService.meetingRoomBookingSummary() >> [today1100To1200]
        controller = new MeetingRoomController(bookingService)

        when:
        def response = controller.getMeetingRoomBookingRecords()

        then:
        response && response.statusCode == 1 && response.status == 'success'
        response.data.size() == 1
        response.data.get(0).begin == CalendarUtil.time1100
        response.data.get(0).end == CalendarUtil.time1200
    }

    def 'Can book meeting room when no reservation'() {
        given:
        MeetingRoomBookingRequest bookingRequest = MeetingRoomBookingRequest.builder()
                .begin(CalendarUtil.time1100)
                .end(CalendarUtil.time1200)
                .build()
        bookingService = Mock(BookingService)
        controller = new MeetingRoomController(bookingService)

        when:
        def response = controller.book(bookingRequest)

        then:
        response && response.statusCode == 1 && response.status == 'success'
    }
}