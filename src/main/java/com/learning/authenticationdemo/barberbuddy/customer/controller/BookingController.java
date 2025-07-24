package com.learning.authenticationdemo.barberbuddy.customer.controller;

import com.learning.authenticationdemo.barberbuddy.customer.model.Booking;
import com.learning.authenticationdemo.barberbuddy.customer.model.BookingStatus;
import com.learning.authenticationdemo.barberbuddy.customer.model.mapper.BookingMapper;
import com.learning.authenticationdemo.barberbuddy.customer.model.mapper.RescheduleMapper;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.request.BookingRequestVO;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.request.RescheduleRequestVO;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.response.BookingResponseVO;
import com.learning.authenticationdemo.barberbuddy.customer.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingMapper bookingMapper;

    @Autowired
    private RescheduleMapper rescheduleMapper;

    @PostMapping("/book")
    public ResponseEntity<Map<String, Object>> bookSlot(@RequestBody BookingRequestVO bookingRequestVO) {
        String response = bookingService.bookSlot(bookingMapper.toEntity(bookingRequestVO));
        return ResponseEntity.ok(Map.of("message", response));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<BookingResponseVO>> getBookings(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(bookingService.getBookings(userId).stream().map(bookingMapper::toResponseVO).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> cancelBooking(@PathVariable("id") Long id) {
        String response = bookingService.cancelBooking(id);
        return ResponseEntity.ok(Map.of("message", response));
    }

    @GetMapping("/saloon/{saloonId}")
    public ResponseEntity<List<BookingResponseVO>> getBookingBySaloonId(@PathVariable("saloonId") Long saloonId) {
        return ResponseEntity.ok(bookingService.fetchBookingForSaloon(saloonId).stream().map(bookingMapper::toResponseVO).toList());
    }

    @PatchMapping("/reschedule")
    public ResponseEntity<Map<String, Object>> reschedule(@RequestBody RescheduleRequestVO request) {
        String response = bookingService.rescheduleBooking(rescheduleMapper.toEntity(request));
        return ResponseEntity.ok(Map.of("message", response));
    }

    @Scheduled(cron = "0 0 * * * *") // For every hour
    public void completePastBookings() {
        List<Booking> bookings = bookingService.getBookingByStatus(BookingStatus.BOOKED.name());

        for (Booking booking : bookings) {
            LocalDateTime slotTime = LocalDateTime.of(booking.getDate(), booking.getTime());
            if (slotTime.isBefore(LocalDateTime.now())) {
                booking.setStatus(BookingStatus.COMPLETED.name());
                bookingService.updateBooking(booking);
            }
        }
    }
}
