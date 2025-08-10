package com.learning.authenticationdemo.barberbuddy.barber.service;

import com.learning.authenticationdemo.barberbuddy.customer.model.Saloon;
import com.learning.authenticationdemo.barberbuddy.customer.model.Slot;
import com.learning.authenticationdemo.barberbuddy.customer.repo.SaloonRepository;
import com.learning.authenticationdemo.barberbuddy.customer.repo.SlotRepository;
import com.learning.authenticationdemo.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BarberService {

    @Autowired
    private SaloonRepository saloonRepository;

    @Autowired
    private SlotRepository slotRepository;

    public Saloon createSaloon(Saloon saloon) {
        Saloon savedSaloon = saloonRepository.save(saloon);

        // Define working hours (e.g., 9 AM to 5 PM)
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        Duration slotDuration = Duration.ofMinutes(30);

        List<Slot> slots = new ArrayList<>();
        LocalTime currentTime = startTime;

        // Create slots only for today (you can loop for multiple days if needed)
        LocalDate slotDate = LocalDate.now();

        while (!currentTime.isAfter(endTime.minus(slotDuration))) {
            Slot slot = new Slot();
            slot.setDate(slotDate);
            slot.setTime(currentTime);
            slot.setSaloon(savedSaloon);
            slot.setBooked(false); // default as false
            slots.add(slot);

            currentTime = currentTime.plus(slotDuration);
        }

        slotRepository.saveAll(slots); // save all the generated slots

        return savedSaloon;
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

    public List<Slot> fetchSlotsBySaloonId(Long saloonId) {
        return slotRepository.findBySaloonId(saloonId);
    }

    public Saloon updateSaloon(Saloon saloon) {
        Saloon existing = saloonRepository.findById(saloon.getId())
                .orElseThrow(() -> new AppException("Saloon not found"));

        // Ensure this saloon belongs to the barber
        if (existing.getBarber().getId() != saloon.getBarber().getId()) {
            throw new AppException(String.format("This Saloon doesn't belongs to barber %s", saloon.getBarber().getName()));
        }

        // Update fields
        existing.setName(saloon.getName());
        existing.setAddress(saloon.getAddress());
        return saloonRepository.save(existing);

    }
}
