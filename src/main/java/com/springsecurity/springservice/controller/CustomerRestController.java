package com.springsecurity.springservice.controller;

import com.springsecurity.springservice.dtos.CustomerDTO;
import com.springsecurity.springservice.exceptions.CustomerNotFoundException;
import com.springsecurity.springservice.service.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping(value = "/customers")
    private List<CustomerDTO> customers(){
        return bankAccountService.listCustomer();
    }

    @GetMapping(value = "/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
       return bankAccountService.getCustomer(customerId);
    }

    @PostMapping(value = "/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
      return bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping(value = "/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
       return bankAccountService.updatedCustomer(customerDTO);
    }

    @DeleteMapping(value = "/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        bankAccountService.deleteCustomer(customerId);
    }
}
