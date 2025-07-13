package com.learning.authenticationdemo.barberbuddy.service;

import com.learning.authenticationdemo.barberbuddy.model.Saloon;
import com.learning.authenticationdemo.barberbuddy.repo.SaloonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private SaloonRepository saloonRepository;

    public List<Saloon> fetchAllSaloons() {
        return saloonRepository.findAll();
    }
}
