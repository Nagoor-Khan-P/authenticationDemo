package com.learning.authenticationdemo.barberbuddy.customer.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long saloonId;
    private LocalDate date;
    private LocalTime time;
    private Long slotId;
    private String status;
    private LocalDateTime bookedAt;

    @PrePersist
    public void prePersist() {
        this.bookedAt = LocalDateTime.now();
    }

    public Booking() {
        // No args constructor
    }

    public Booking(Long id, Long userId, Long saloonId, LocalDate date, LocalTime time, Long slotId, String status, LocalDateTime bookedAt) {
        this.id = id;
        this.userId = userId;
        this.saloonId = saloonId;
        this.date = date;
        this.time = time;
        this.slotId = slotId;
        this.status = status;
        this.bookedAt = bookedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", userId=" + userId +
                ", saloonId=" + saloonId +
                ", date=" + date +
                ", time=" + time +
                ", slotId=" + slotId +
                ", status='" + status + '\'' +
                ", bookedAt=" + bookedAt +
                '}';
    }
}
