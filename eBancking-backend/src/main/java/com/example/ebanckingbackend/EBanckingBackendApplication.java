package com.example.ebanckingbackend;

import com.example.ebanckingbackend.entities.AccountOperation;
import com.example.ebanckingbackend.entities.CurrentAccount;
import com.example.ebanckingbackend.entities.Customer;
import com.example.ebanckingbackend.entities.SavingAccount;
import com.example.ebanckingbackend.enums.AccountStatus;
import com.example.ebanckingbackend.enums.OperationType;
import com.example.ebanckingbackend.repositories.AccountOperationRepository;
import com.example.ebanckingbackend.repositories.BankAccountRepository;
import com.example.ebanckingbackend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBanckingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBanckingBackendApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository,
    AccountOperationRepository accountOperationRepository){
        return args->{
            Stream.of("med","medo","moha").forEach(name->{
                Customer customer=new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(customer->{
                //create saving account
                SavingAccount sa=new SavingAccount();
                sa.setId(UUID.randomUUID().toString());
                sa.setCustomer(customer);
                sa.setBalance(Math.random()*95700);
                sa.setInterestRate(4.5);
                sa.setCreatedAt(new Date());
                sa.setStatus(AccountStatus.CREATED);
                bankAccountRepository.save(sa);
                //create current account
                CurrentAccount ca=new CurrentAccount();
                ca.setId(UUID.randomUUID().toString());
                ca.setCustomer(customer);
                ca.setBalance(Math.random()*88000);
                ca.setCreatedAt(new Date());
                ca.setOverDraft(88000);
                ca.setStatus(AccountStatus.CREATED);
                bankAccountRepository.save(ca);
                //create 5 operation for each BankAccount
                bankAccountRepository.findAll().forEach(acc->{
                    for (int i = 0; i <10 ; i++) {
                        AccountOperation accountOperation=new AccountOperation();
                        accountOperation.setOperationDate(new Date());
                        accountOperation.setAmount(Math.random()*12000);
                        accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT);
                        accountOperation.setBankAccount(acc);
                        accountOperationRepository.save(accountOperation);
                    }

                });
            });
        };
    }
}
