package com.example.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by deadlock on 27/1/17.
 */
@Data
@Builder
public class ItemBookingInfo {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @JsonProperty("reserved_slots")
    private List<Integer> reservedSlots;
}
