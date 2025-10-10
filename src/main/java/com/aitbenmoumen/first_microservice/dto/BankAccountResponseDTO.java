package com.aitbenmoumen.first_microservice.dto;

import com.aitbenmoumen.first_microservice.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountResponseDTO {
    private String id;
    private Date createdAt;
    private Double balance;
    private String currency;
    private AccountType type;
}
