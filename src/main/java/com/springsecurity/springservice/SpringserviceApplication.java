package com.springsecurity.springservice;

import com.springsecurity.springservice.dtos.CustomerDTO;
import com.springsecurity.springservice.entity.*;
import com.springsecurity.springservice.enums.AccountStatus;
import com.springsecurity.springservice.enums.OperationType;
import com.springsecurity.springservice.exceptions.BalanceNotSufficientException;
import com.springsecurity.springservice.exceptions.BankAccountNotFoundException;
import com.springsecurity.springservice.exceptions.CustomerNotFoundException;
import com.springsecurity.springservice.repository.AccountOperationRepository;
import com.springsecurity.springservice.repository.BankAccountRepository;
import com.springsecurity.springservice.repository.CustomerRepository;
import com.springsecurity.springservice.service.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication

public class SpringserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringserviceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
		return args -> {
			Stream.of("Hassan", "Yassine", "Aicha").forEach(name -> {
				CustomerDTO customerDTO = new CustomerDTO();
				customerDTO.setName(name);
				customerDTO.setEmail(name + "@gmail.com");
				bankAccountService.saveCustomer(customerDTO);
			});
			bankAccountService.listCustomer().forEach(cust -> {
				try {
					bankAccountService.saveCurrentBankAccount(Math.random() * 90000, 9000, cust.getId());
					bankAccountService.saveSavingBankAccount(Math.random() * 120000, 5.5, cust.getId());
					List<BankAccount> bankAccounts = bankAccountService.bankAccountList();
					for(BankAccount bankAccount: bankAccounts){
						for (int i = 0; i < 10; i++) {
							bankAccountService.credit(bankAccount.getId(), 10000 + Math.random() * 1200000, "Credit");
							bankAccountService.debit(bankAccount.getId(), 1000+Math.random()*9000, "Débit");
						}
					}
				} catch (CustomerNotFoundException e) {
					e.printStackTrace();
				} catch (BankAccountNotFoundException | BalanceNotSufficientException e) {
					throw new RuntimeException();
				}

			});


			/**


			 @Bean CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository,
			 AccountOperationRepository accountOperationRepository){
			 return args -> {
			 Stream.of("Hassan", "Yassine", "Aicha").forEach(name -> {
			 Customer customer = new Customer();
			 customer.setName(name);
			 customer.setEmail(name+"@gmail.com");
			 customerRepository.save(customer);
			 });
			 customerRepository.findAll().forEach(cust ->{
			 CurrentAccount currentAccount = new CurrentAccount();
			 currentAccount.setId(UUID.randomUUID().toString());
			 currentAccount.setBalance(Math.random()*9000);
			 currentAccount.setCreated(new Date());
			 currentAccount.setStatus(AccountStatus.CREATED);
			 currentAccount.setCustomer(cust);
			 currentAccount.setOverDraft(9000);
			 bankAccountRepository.save(currentAccount);

			 SavingAccount savingAccount = new SavingAccount();
			 savingAccount.setId(UUID.randomUUID().toString());
			 savingAccount.setBalance(Math.random()*9000);
			 savingAccount.setCreated(new Date());
			 savingAccount.setStatus(AccountStatus.CREATED);
			 savingAccount.setCustomer(cust);
			 savingAccount.setInterestRate(5.5);
			 bankAccountRepository.save(savingAccount);
			 });

			 // Je récupère les comptes dans ma base de donnée et je fais une boucle pour créer 5 opérations
			 bankAccountRepository.findAll().forEach(acc->{
			 for (int i = 0; i < 10; i++){
			 AccountOperation accountOperation = new AccountOperation();
			 accountOperation.setOperationDate(new Date());
			 accountOperation.setAmount(Math.random()* 12000);
			 accountOperation.setType(Math.random()>0.5? OperationType.DEBIT : OperationType.CREDIT);
			 accountOperation.setBankAccount(acc);
			 accountOperationRepository.save(accountOperation);
			 }
			 });
			 };
			 }

			 */
		};


	}
}