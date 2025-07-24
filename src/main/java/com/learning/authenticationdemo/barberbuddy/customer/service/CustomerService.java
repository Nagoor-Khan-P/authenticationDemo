package com.learning.authenticationdemo.barberbuddy.customer.service;

import com.learning.authenticationdemo.barberbuddy.customer.model.Saloon;
import com.learning.authenticationdemo.barberbuddy.customer.model.Slot;
import com.learning.authenticationdemo.barberbuddy.customer.repo.SaloonRepository;
import com.learning.authenticationdemo.barberbuddy.customer.repo.SlotRepository;
import com.learning.authenticationdemo.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private SaloonRepository saloonRepository;

    @Autowired
    private SlotRepository slotRepository;

    public List<Saloon> fetchAllSaloons() {
        return saloonRepository.findAll();
    }

    public Saloon fetchSaloonById(Long saloonId) {
        Optional<Saloon> saloon = saloonRepository.findById(saloonId);
        if (saloon.isEmpty()) {
            throw new AppException("Saloon is not available!!");
        }
        return saloon.get();
    }

    public List<Slot> fetchAvailableSlots(Long saloonId) {
        return slotRepository.findBySaloonIdAndBookedFalse(saloonId);
    }
}
