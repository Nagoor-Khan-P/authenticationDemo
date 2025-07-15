package com.learning.authenticationdemo.barberbuddy.customer.service;

import com.learning.authenticationdemo.barberbuddy.customer.model.Booking;
import com.learning.authenticationdemo.barberbuddy.customer.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public String bookSlot(Booking booking) {
        boolean alreadyBooked = bookingRepository.existsBySlotIdAndDateAndTime(
                booking.getSlotId(), booking.getDate(), booking.getTime());

        if (alreadyBooked) {
            return "Slot already booked.";
        }

        booking.setStatus("Booked");
        bookingRepository.save(booking);
        return "Booking successful!";
    }

    public List<Booking> getBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public String cancelBooking(Long id) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            booking.setStatus("CANCELLED");
            bookingRepository.save(booking);
            return "Booking cancelled.";
        } else {
            return "Booking not found.";
        }
    }
}
