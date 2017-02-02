package com.example.persist.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by deadlock on 28/1/17.
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingItem {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}
