package com.example.persist.repository;

import com.example.persist.entity.BookingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by deadlock on 28/1/17.
 */
@Repository
public interface BookingItemRepository extends JpaRepository<BookingItem, Integer> {
}
