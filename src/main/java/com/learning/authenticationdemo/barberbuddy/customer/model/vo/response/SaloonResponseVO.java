package com.learning.authenticationdemo.barberbuddy.customer.model.vo.response;

public class SaloonResponseVO {

    private Long id;
    private String name;
    private String address;
    private String barberUsername;

    public SaloonResponseVO(Long id, String name, String address, String barberUsername) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.barberUsername = barberUsername;
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
}
