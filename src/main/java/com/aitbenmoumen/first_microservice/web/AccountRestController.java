package com.aitbenmoumen.first_microservice.web;

import com.aitbenmoumen.first_microservice.dto.BankAccountRequestDTO;
import com.aitbenmoumen.first_microservice.dto.BankAccountResponseDTO;
import com.aitbenmoumen.first_microservice.entities.BankAccount;
import com.aitbenmoumen.first_microservice.repositories.BankAccountRepository;
import com.aitbenmoumen.first_microservice.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bankAccounts")
@RequiredArgsConstructor // FIXED: Re-added @RequiredArgsConstructor for cleaner dependency injection
public class AccountRestController {

    private final BankAccountRepository bankAccountRepository;
    private final BankAccountService bankAccountService;

    // REMOVED: Manual constructor since @RequiredArgsConstructor handles dependency injection automatically

    @GetMapping
    public ResponseEntity<List<BankAccount>> getBankAccounts() {
        List<BankAccount> accounts = bankAccountRepository.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getBankAccount(@PathVariable String id) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<BankAccountResponseDTO> createBankAccount(@RequestBody BankAccountRequestDTO bankAccount) {
        BankAccountResponseDTO b = bankAccountService.addAccount(bankAccount);
        return ResponseEntity.ok(b);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable String id,
                                                         @RequestBody BankAccount bankAccount) {
        BankAccount existing = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (bankAccount.getType() != null) existing.setType(bankAccount.getType());
        if (bankAccount.getBalance() != null) existing.setBalance(bankAccount.getBalance());
        if (bankAccount.getCurrency() != null && !bankAccount.getCurrency().isBlank()) {
            existing.setCurrency(bankAccount.getCurrency());
        }

        if (bankAccount.getCreatedAt() != null) existing.setCreatedAt(bankAccount.getCreatedAt());

        BankAccount updated = bankAccountRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable String id) {
        if (!bankAccountRepository.existsById(id)) {
            throw new RuntimeException("Account not found");
        }
        bankAccountRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
