package com.example.controller

import com.example.service.BookingService
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

}