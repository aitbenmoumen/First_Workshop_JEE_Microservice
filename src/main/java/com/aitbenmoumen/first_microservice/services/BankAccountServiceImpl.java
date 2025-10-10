package com.aitbenmoumen.first_microservice.services;

import com.aitbenmoumen.first_microservice.dto.BankAccountRequestDTO;
import com.aitbenmoumen.first_microservice.dto.BankAccountResponseDTO;
import com.aitbenmoumen.first_microservice.entities.BankAccount;
import com.aitbenmoumen.first_microservice.repositories.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount b = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(b, bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }
}
