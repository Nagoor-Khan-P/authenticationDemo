package com.learning.authenticationdemo.barberbuddy.customer.model.vo.request;

public class SaloonRequestVO {
    private String name;
    private String address;

    public SaloonRequestVO(String name, String address) {
        this.name = name;
        this.address = address;
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
}
