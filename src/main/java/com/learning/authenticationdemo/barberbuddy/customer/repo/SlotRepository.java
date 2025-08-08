package com.learning.authenticationdemo.barberbuddy.customer.repo;

import com.learning.authenticationdemo.barberbuddy.customer.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

    List<Slot> findBySaloonIdAndBookedFalse(Long saloonId);

    List<Slot> findBySaloonId(Long saloonId);

}
