package com.learning.authenticationdemo.barberbuddy.customer.model.vo.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class SlotRequestVO {

    private LocalDate date;
    private LocalTime time;
    private Long saloonId;

    public SlotRequestVO(LocalDate date, LocalTime time, Long saloonId) {
        this.date = date;
        this.time = time;
        this.saloonId = saloonId;
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

    public Long getSaloonId() {
        return saloonId;
    }

    public void setSaloonId(Long saloonId) {
        this.saloonId = saloonId;
    }
}
