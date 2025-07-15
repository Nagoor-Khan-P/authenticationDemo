package com.learning.authenticationdemo.barberbuddy.customer.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "slots")
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime time;

    private boolean booked = false;

    @ManyToOne
    @JoinColumn(name = "saloon_id")
    private Saloon saloon;

    public Slot() {
        // No arg constructor
    }

    public Slot(Long id, LocalDate date, LocalTime time, boolean booked, Saloon saloon) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.booked = booked;
        this.saloon = saloon;
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

    public Saloon getSaloon() {
        return saloon;
    }

    public void setSaloon(Saloon saloon) {
        this.saloon = saloon;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", booked=" + booked +
                ", saloon=" + saloon +
                '}';
    }
}
