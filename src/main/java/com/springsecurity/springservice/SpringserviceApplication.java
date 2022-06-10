package com.springsecurity.springservice;

import com.springsecurity.springservice.entity.AccountOperation;
import com.springsecurity.springservice.entity.CurrentAccount;
import com.springsecurity.springservice.entity.Customer;
import com.springsecurity.springservice.entity.SavingAccount;
import com.springsecurity.springservice.enums.AccountStatus;
import com.springsecurity.springservice.enums.OperationType;
import com.springsecurity.springservice.repository.AccountOperationRepository;
import com.springsecurity.springservice.repository.BankAccountRepository;
import com.springsecurity.springservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication

public class SpringserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringserviceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository,
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



}