package com.example.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by deadlock on 27/1/17.
 */
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private List<Bookable> bookables = new ArrayList<>();

    public Integer add(Bookable bookable) {
        bookables.add(bookable);
        return bookable.id();
    }

    public Optional<Bookable> get(Integer id) {
        return bookables.stream().filter(bookable -> id.equals(bookable.id())).findFirst();
    }

    public void delete(Integer id) {
        bookables = bookables.stream().filter(bookable -> !id.equals(bookable.id())).collect(Collectors.toList());
    }

    public Integer count() {
        return bookables.size();
    }
}
