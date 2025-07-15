package com.learning.authenticationdemo.barberbuddy.customer.repo;

import com.learning.authenticationdemo.barberbuddy.customer.model.Saloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaloonRepository extends JpaRepository<Saloon, Long> {
    List<Saloon> findAll();
}
