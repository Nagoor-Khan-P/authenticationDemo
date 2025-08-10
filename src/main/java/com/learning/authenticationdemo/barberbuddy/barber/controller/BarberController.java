package com.learning.authenticationdemo.barberbuddy.barber.controller;


import com.learning.authenticationdemo.barberbuddy.barber.service.BarberService;
import com.learning.authenticationdemo.barberbuddy.customer.model.Saloon;
import com.learning.authenticationdemo.barberbuddy.customer.model.Slot;
import com.learning.authenticationdemo.barberbuddy.customer.model.mapper.SaloonMapper;
import com.learning.authenticationdemo.barberbuddy.customer.model.mapper.SlotMapper;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.request.SaloonRequestVO;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.request.SlotRequestVO;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.response.SaloonResponseVO;
import com.learning.authenticationdemo.barberbuddy.customer.model.vo.response.SlotResponseVO;
import com.learning.authenticationdemo.model.Users;
import com.learning.authenticationdemo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barber")
public class BarberController {

    @Autowired
    private SaloonMapper saloonMapper;

    @Autowired
    private SlotMapper slotMapper;

    @Autowired
    private BarberService barberService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping("/saloon")
    public ResponseEntity<SaloonResponseVO> createSaloon(@RequestBody SaloonRequestVO saloonRequestVO, Authentication authentication) {
        String barberName = authentication.getName();
        Users barber = myUserDetailsService.fetchUserByUserName(barberName);
        Saloon saloon = saloonMapper.toEntity(saloonRequestVO);
        saloon.setBarber(barber);
        return ResponseEntity.ok(saloonMapper.toResponse(barberService.createSaloon(saloon)));
    }

    @PutMapping("/saloon/{saloonId}")
    public ResponseEntity<SaloonResponseVO> updateSaloon(
            @PathVariable Long saloonId,
            @RequestBody SaloonRequestVO saloonRequestVO, Authentication authentication
    ) {
        String barberName = authentication.getName();
        Users barber = myUserDetailsService.fetchUserByUserName(barberName);
        Saloon saloon = saloonMapper.toEntity(saloonRequestVO);
        saloon.setId(saloonId);
        saloon.setBarber(barber);
        return ResponseEntity.ok(saloonMapper.toResponse(barberService.updateSaloon(saloon)));
    }
    @GetMapping("/saloon/{saloonId}")
    public ResponseEntity<SaloonResponseVO> fetchSaloon(@PathVariable("saloonId") Long saloonId) {
        return ResponseEntity.ok(saloonMapper.toResponse(barberService.fetchSaloonById(saloonId)));
    }

    @GetMapping("/saloon/{saloonId}/slots")
    public ResponseEntity<List<SlotResponseVO>> fetchSlotsBySaloon(@PathVariable("saloonId") Long saloonId) {
        return ResponseEntity.ok(barberService.fetchSlotsBySaloonId(saloonId).stream().map(slotMapper::toResponse).toList());
    }

    @GetMapping("/{barberId}/saloons")
    public ResponseEntity<List<SaloonResponseVO>> fetchSaloonsOfBarber(@PathVariable("barberId") Long barberId) {
        return ResponseEntity.ok(barberService.getSaloonsOfBarber(barberId).stream().map(saloonMapper::toResponse).toList());
    }

    @PostMapping("/saloon/slot")
    public ResponseEntity<SlotResponseVO> addSlotToSaloon(
            @RequestBody SlotRequestVO slotRequestVO) {
        Long saloonId = slotRequestVO.getSaloonId();
        Slot slot = barberService.createSlot(slotMapper.toEntity(slotRequestVO), saloonId);
        return ResponseEntity.ok(slotMapper.toResponse(slot));
    }
}
