package com.aitbenmoumen.first_microservice.entities;

import com.aitbenmoumen.first_microservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = BankAccount.class, name = "p1") // it allows you to specify fields you want form BankAccount entity
public interface AccoutProjection {
    public String getId();
    public AccountType getType();
}
// you may call this by "localhost:8081/bankAccounts?projection=p1"
