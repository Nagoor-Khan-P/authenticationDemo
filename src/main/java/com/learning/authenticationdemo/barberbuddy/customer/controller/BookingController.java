package com.learning.authenticationdemo.barberbuddy.customer.controller;

import com.learning.authenticationdemo.barberbuddy.customer.model.mapper.BookingMapper;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.request.BookingRequestVO;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.response.BookingResponseVO;
import com.learning.authenticationdemo.barberbuddy.customer.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingMapper bookingMapper;

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
}
