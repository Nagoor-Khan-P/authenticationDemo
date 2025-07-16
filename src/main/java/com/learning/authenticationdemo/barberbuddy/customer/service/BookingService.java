package com.learning.authenticationdemo.barberbuddy.customer.service;

import com.learning.authenticationdemo.barberbuddy.customer.model.Booking;
import com.learning.authenticationdemo.barberbuddy.customer.model.BookingStatus;
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

        booking.setStatus(BookingStatus.BOOKED.name());
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
            booking.setStatus(BookingStatus.CANCELLED.name());
            bookingRepository.save(booking);
            return "Booking cancelled.";
        } else {
            return "Booking not found.";
        }
    }

    public List<Booking> fetchBookingForSaloon(Long saloonId) {
        return bookingRepository.findBySaloonId(saloonId);
    }

    public String rescheduleBooking(Booking newBooking) {
        Booking booking = bookingRepository.findById(newBooking.getId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        boolean alreadyBooked = bookingRepository.existsBySlotIdAndDateAndTime(
                newBooking.getSlotId(), newBooking.getDate(), newBooking.getTime());

        if (alreadyBooked) {
            return "Requested new slot is already booked.";
        }
        booking.setDate(newBooking.getDate());
        booking.setTime(newBooking.getTime());
        booking.setSlotId(newBooking.getSlotId());
        booking.setStatus(BookingStatus.RESCHEDULED.name());

        bookingRepository.save(booking);
        return "Booking rescheduled successfully.";
    }

    public void updateBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public List<Booking> getBookingByStatus(String booked) {
        return bookingRepository.findByStatus(booked);
    }
}
