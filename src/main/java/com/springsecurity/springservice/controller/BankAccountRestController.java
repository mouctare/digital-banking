package com.springsecurity.springservice.controller;

import com.springsecurity.springservice.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BankAccountRestController {
    private BankAccountService bankAccountService;

}
