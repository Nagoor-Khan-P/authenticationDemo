package com.learning.authenticationdemo.model;

import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    private long id;

    @Column
    private String name;
    @Column
    private String description;

    public Role() {
        // No arg constructor
    }

    // Getter for id
    public long getId() {
        return id;
    }

    // Setter for id
    public void setId(long id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }
}
