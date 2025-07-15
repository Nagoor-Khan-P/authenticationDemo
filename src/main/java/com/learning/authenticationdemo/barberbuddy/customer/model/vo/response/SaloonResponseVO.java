package com.learning.authenticationdemo.barberbuddy.customer.model.vo.response;

import java.time.LocalDateTime;

public class SaloonResponseVO {

    private Long id;
    private String name;
    private String address;
    private String barberUsername;

    private LocalDateTime createdAt;

    public SaloonResponseVO(Long id, String name, String address, String barberUsername, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.barberUsername = barberUsername;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBarberUsername() {
        return barberUsername;
    }

    public void setBarberUsername(String barberUsername) {
        this.barberUsername = barberUsername;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
