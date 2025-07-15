package com.learning.authenticationdemo.barberbuddy.customer.service;

import com.learning.authenticationdemo.barberbuddy.customer.model.Saloon;
import com.learning.authenticationdemo.barberbuddy.customer.model.Slot;
import com.learning.authenticationdemo.barberbuddy.customer.repo.SaloonRepository;
import com.learning.authenticationdemo.barberbuddy.customer.repo.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private SaloonRepository saloonRepository;

    @Autowired
    private SlotRepository slotRepository;

    public List<Saloon> fetchAllSaloons() {
        return saloonRepository.findAll();
    }

    public List<Slot> fetchAvailableSlots(Long saloonId) {
        return slotRepository.findBySaloonIdAndBookedFalse(saloonId);
    }
}
