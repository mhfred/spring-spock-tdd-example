package com.example.service;

import com.example.model.BookingItem;
import com.example.persist.repository.BookingItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingItemFactory {
    private BookingItemRepository bookingItemRepository;

    public List<BookingItem> createFromEntity() {
        List<com.example.persist.entity.BookingItem> bookingItemEntities = bookingItemRepository.findAll();
        return bookingItemEntities.stream().map(
                entity ->
                        BookingItem.builder()
                                .id(entity.getId())
                                .name(entity.getName())
                                .build()
        ).collect(Collectors.toList());
    }
}
