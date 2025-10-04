package com.aitbenmoumen.first_microservice.repositories;

import com.aitbenmoumen.first_microservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
