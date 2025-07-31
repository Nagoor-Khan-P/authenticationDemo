package com.learning.authenticationdemo.barberbuddy.barber.service;

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
public class BarberService {

    @Autowired
    private SaloonRepository saloonRepository;

    @Autowired
    private SlotRepository slotRepository;

    public Saloon createSaloon(Saloon saloon) {
        return saloonRepository.save(saloon);
    }

    public Slot createSlot(Slot slot, Long saloonId) {
        Optional<Saloon> saloon = saloonRepository.findById(saloonId);
        if(saloon.isEmpty()) {
            throw new AppException("Saloon with id: " + saloonId + " not found!");
        }
        slot.setSaloon(saloon.get());
        return slotRepository.save(slot);
    }

    public List<Saloon> getSaloonsOfBarber(Long barberId) {
        return saloonRepository.findByBarberId(barberId);
    }

    public Saloon fetchSaloonById(Long saloonId) {
        Optional<Saloon> saloon = saloonRepository.findById(saloonId);
        if (saloon.isEmpty()) {
            throw new AppException("Saloon not found with id: " + saloonId);
        }
        return saloon.get();
    }
}
