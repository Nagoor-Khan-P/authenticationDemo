package com.learning.authenticationdemo.barberbuddy.customer.model.vo.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class SlotResponseVO {

    private Long id;
    private LocalDate date;
    private LocalTime time;
    private boolean booked;
    private Long saloonId;

    public SlotResponseVO(Long id, LocalDate date, LocalTime time, boolean booked, Long saloonId) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.booked = booked;
        this.saloonId = saloonId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public Long getSaloonId() {
        return saloonId;
    }

    public void setSaloonId(Long saloonId) {
        this.saloonId = saloonId;
    }
}
