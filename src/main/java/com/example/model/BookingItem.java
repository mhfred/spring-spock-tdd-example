package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by deadlock on 27/1/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingItem {
    private Integer id;
    private String name;
}
