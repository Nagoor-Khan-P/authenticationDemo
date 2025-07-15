package com.learning.authenticationdemo.barberbuddy.customer.model;

import com.learning.authenticationdemo.model.Users;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "saloons")
public class Saloon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @ManyToOne
    @JoinColumn(name = "barber_id", nullable = false)
    private Users barber;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Saloon() {
        // No Arg Constructor
    }

    public Saloon(Long id, String name, String address, Users barber, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.barber = barber;
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

    public Users getBarber() {
        return barber;
    }

    public void setBarber(Users barber) {
        this.barber = barber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Saloon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", barber=" + barber +
                ", createdAt=" + createdAt +
                '}';
    }
}
