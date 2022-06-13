package com.springsecurity.springservice.controller;

import com.springsecurity.springservice.entity.Customer;
import com.springsecurity.springservice.service.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping(value = "/customers")
    private List<Customer> customers(){
        return bankAccountService.listCustomer();
    }
}
