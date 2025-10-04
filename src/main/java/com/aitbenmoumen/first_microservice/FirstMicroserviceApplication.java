package com.aitbenmoumen.first_microservice;

import com.aitbenmoumen.first_microservice.entities.BankAccount;
import com.aitbenmoumen.first_microservice.enums.AccountType;
import com.aitbenmoumen.first_microservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class FirstMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstMicroserviceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository repository) {
		return args -> {
			for(int i = 0; i < 10 ;i++){
				BankAccount account = BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.type((Math.random() > 0.5) ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
						.createdAt(new Date())
						.currency("MAD")
						.balance(10000 + Math.random()*90000)
						.build();
				repository.save(account);
			}
		};
	}
}
