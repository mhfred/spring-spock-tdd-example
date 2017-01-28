package com.example.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;

/**
 * Created by deadlock on 27/1/17.
 */
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookableImpl implements Bookable {
    @NonNull
    private BookingItem item;
    @NonNull
    private BookingCalendar calendar;

    @Override
    public Integer id() {
        return 1;
    }

    @Override
    public String name() {
        return "hello";
    }
}
