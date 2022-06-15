package com.springsecurity.springservice.service;

import com.springsecurity.springservice.dtos.*;
import com.springsecurity.springservice.entity.BankAccount;
import com.springsecurity.springservice.entity.CurrentAccount;
import com.springsecurity.springservice.entity.Customer;
import com.springsecurity.springservice.entity.SavingAccount;
import com.springsecurity.springservice.exceptions.BalanceNotSufficientException;
import com.springsecurity.springservice.exceptions.BankAccountNotFoundException;
import com.springsecurity.springservice.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {

     CustomerDTO saveCustomer(CustomerDTO customerDTO);

     /**
      *
      * @param initialBalance
      * @param overDraft
      * @param customerId
      * @return
      * @throws CustomerNotFoundException
      * exception surveillée
      */
     CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
     SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
     // List<Customer> listCustomer();
     List<CustomerDTO> listCustomer();
     BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
     void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
     void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
     void transfert(String accountIdSource, String accountIDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

     List<BankAccountDTO> bankAccountList();

     CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

     CustomerDTO updatedCustomer(CustomerDTO customerDTO);

     void deleteCustomer(Long customerId);

     List<AccountOperationDTO> accountHistory(String accountId);
}
