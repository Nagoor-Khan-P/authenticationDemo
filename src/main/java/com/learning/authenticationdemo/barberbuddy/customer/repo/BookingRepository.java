package com.learning.authenticationdemo.barberbuddy.customer.repo;

import com.learning.authenticationdemo.barberbuddy.customer.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);

    boolean existsBySlotIdAndDateAndTime(Long slotId, LocalDate date, LocalTime time);

    List<Booking> findBySaloonId(Long saloonId);

    List<Booking> findByStatus(String status);
}
