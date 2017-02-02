package com.example.service;

import com.example.controller.request.MeetingRoomBookingRequest;
import com.example.model.Bookable;
import com.example.model.BookableCollection;
import com.example.model.ReservationFailedException;
import com.example.service.dto.BookingRecord;
import com.example.service.exception.BookingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by deadlock on 27/1/17.
 */
@Service
public class BookingService {
    private static int MEETING_ROOM_ID = 1;
    private BookableImplFactory bookableImplFactory;
    private BookableCollection bookableCollection;

    @Autowired
    public BookingService(BookableImplFactory bookableImplFactory) {
        this.bookableImplFactory = bookableImplFactory;

        List<Bookable> bookableList = this.bookableImplFactory.createFromBookingItems();
        bookableCollection = new BookableCollection(bookableList);
    }

    public Optional<Bookable> getBookable(Integer id) {
        return bookableCollection.get(id);
    }

    public void bookMeetingRoom(Calendar begin, Calendar end) throws BookingException, ReservationFailedException {
        Bookable bookable = bookableCollection.get(MEETING_ROOM_ID).orElseThrow(() -> new BookingException("bookable is not found"));
        bookable.book(begin, end);
    }

    public void bookMeetingRoom(MeetingRoomBookingRequest request) throws BookingException, ReservationFailedException {
        bookMeetingRoom(request.getBegin(), request.getEnd());
    }

    public List<BookingRecord> meetingRoomBookingSummary() throws BookingException {
        Bookable bookable = bookableCollection.get(MEETING_ROOM_ID).orElseThrow(() -> new BookingException("bookable is not found"));
        return bookable.getReservations().stream()
                .map(reservation -> BookingRecord.builder()
                        .begin(reservation.getBegin())
                        .end(reservation.getEnd())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
