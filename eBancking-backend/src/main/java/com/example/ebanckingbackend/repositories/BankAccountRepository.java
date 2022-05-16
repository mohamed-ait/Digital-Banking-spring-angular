package com.example.ebanckingbackend.repositories;

import com.example.ebanckingbackend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Base64;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
