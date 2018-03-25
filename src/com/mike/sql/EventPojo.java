package com.mike.sql;

import java.time.LocalDate;

import lombok.Data;

/**
 * Base for event-related POJOs
 */
@Data
public abstract class EventPojo {
    private LocalDate startDate;
    private Integer durationDays;

    public LocalDate getEndDate() {
        return this.startDate.plusDays(durationDays);
    }
}
