package com.springsecurity.springservice.repository;

import com.springsecurity.springservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
