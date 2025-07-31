package com.learning.authenticationdemo.barberbuddy.customer.controller;

import com.learning.authenticationdemo.barberbuddy.customer.model.Saloon;
import com.learning.authenticationdemo.barberbuddy.customer.model.Slot;
import com.learning.authenticationdemo.barberbuddy.customer.model.mapper.SaloonMapper;
import com.learning.authenticationdemo.barberbuddy.customer.model.mapper.SlotMapper;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.response.SaloonResponseVO;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.response.SlotResponseVO;
import com.learning.authenticationdemo.barberbuddy.customer.service.CustomerService;
import com.learning.authenticationdemo.model.Users;
import com.learning.authenticationdemo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SaloonMapper saloonMapper;

    @Autowired
    private SlotMapper slotMapper;

    @GetMapping("/saloons")
    public ResponseEntity<List<SaloonResponseVO>> getAllSaloons() {
        List<Saloon> saloons = customerService.fetchAllSaloons();
        return ResponseEntity.ok(saloons.stream().map(saloonMapper::toResponse).toList());
    }

    @GetMapping("/saloons/{saloonId}")
    public ResponseEntity<SaloonResponseVO> getSaloon(@PathVariable("saloonId") Long saloonId) {
        Saloon saloon = customerService.fetchSaloonById(saloonId);
        return ResponseEntity.ok(saloonMapper.toResponse(saloon));
    }

    @GetMapping("/saloons/{saloonId}/slots")
    public ResponseEntity<List<SlotResponseVO>> getAvailableSlots(@PathVariable("saloonId") Long saloonId) {
        List<Slot> slotsAvailable = customerService.fetchAvailableSlots(saloonId);
        return ResponseEntity.ok(slotsAvailable.stream().map(slotMapper::toResponse).toList());
    }

}
