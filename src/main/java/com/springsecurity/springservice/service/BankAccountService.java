package com.springsecurity.springservice.service;

import com.springsecurity.springservice.entity.BankAccount;
import com.springsecurity.springservice.entity.CurrentAccount;
import com.springsecurity.springservice.entity.Customer;
import com.springsecurity.springservice.entity.SavingAccount;
import com.springsecurity.springservice.exceptions.BalanceNotSufficientException;
import com.springsecurity.springservice.exceptions.BankAccountNotFoundException;
import com.springsecurity.springservice.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
     Customer saveCustomer(Customer customer);

     /**
      *
      * @param initialBalance
      * @param overDraft
      * @param customerId
      * @return
      * @throws CustomerNotFoundException
      * exception surveillée
      */
     CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
     SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
     List<Customer> listCustomer();
     BankAccount getBankAccount(String accountId) throws BankAccountNotFoundException;
     void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
     void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;
     void transfert(String accountIdSource, String accountIDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException;

     List<BankAccount> bankAccountList();
}
