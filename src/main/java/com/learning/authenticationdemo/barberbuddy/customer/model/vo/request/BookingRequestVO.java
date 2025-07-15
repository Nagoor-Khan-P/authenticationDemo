package com.learning.authenticationdemo.barberbuddy.customer.model.vo.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingRequestVO {

    private Long userId;
    private Long saloonId;
    private Long slotId;
    private LocalDate date;
    private LocalTime time;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSaloonId() {
        return saloonId;
    }

    public void setSaloonId(Long saloonId) {
        this.saloonId = saloonId;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
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
}
