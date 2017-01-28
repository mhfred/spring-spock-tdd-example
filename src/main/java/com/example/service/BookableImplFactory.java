package com.example.service;

import com.example.model.Bookable;
import com.example.model.BookableImpl;
import com.example.model.BookingCalendar;
import com.example.model.BookingItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by deadlock on 28/1/17.
 */
@Service
@AllArgsConstructor
public class BookableImplFactory {
    private BookingItemFactory bookingItemFactory;

    public List<Bookable> createFromBookingItems() {
        List<BookingItem> bookingItemList = bookingItemFactory.createFromEntity();
        return bookingItemList.stream()
                .map(booingItem ->
                        BookableImpl.builder()
                                .item(booingItem)
                                .calendar(new BookingCalendar())
                                .build()
                )
                .collect(Collectors.toList());
    }
}
