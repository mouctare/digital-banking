package com.springsecurity.springservice.controller;

import com.springsecurity.springservice.dtos.AccountHistoryDTO;
import com.springsecurity.springservice.dtos.AccountOperationDTO;
import com.springsecurity.springservice.dtos.BankAccountDTO;
import com.springsecurity.springservice.exceptions.BankAccountNotFoundException;
import com.springsecurity.springservice.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRestController {
    private BankAccountService bankAccountService;

    @GetMapping(value = "/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);

    }

    @GetMapping(value = "/accounts")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();

    }

    /**
     * Je cherche à consulter les opérations de ce compte
     * @param accountId
     * @return
     */
    @GetMapping(value = "/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping(value = "/accounts/{accountId}/pageOperations")
    public AccountHistoryDTO getAccountHistory(
            @PathVariable String accountId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "page", defaultValue = "5") int size) throws BankAccountNotFoundException {
        return  bankAccountService.getBankAccountHistory(accountId, page, size);

    }

}
