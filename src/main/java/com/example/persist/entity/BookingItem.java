package com.example.persist.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by deadlock on 28/1/17.
 */
@Entity
@Data
@Builder
public class BookingItem {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}
