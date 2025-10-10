package com.aitbenmoumen.first_microservice.services;

import com.aitbenmoumen.first_microservice.dto.BankAccountRequestDTO;
import com.aitbenmoumen.first_microservice.dto.BankAccountResponseDTO;
import com.aitbenmoumen.first_microservice.entities.BankAccount;

public interface BankAccountService {
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
