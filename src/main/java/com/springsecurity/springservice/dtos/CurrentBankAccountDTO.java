package com.springsecurity.springservice.dtos;

import com.springsecurity.springservice.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class CurrentBankAccountDTO extends BankAccountDTO{
    private String id;
    private double balance;
    private Date created;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;
}
