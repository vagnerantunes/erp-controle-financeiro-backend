package com.erp.controle.financeiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ErpControleFinanceiroApplication {
	public static void main(String[] args) {

		SpringApplication.run(ErpControleFinanceiroApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("senha123"));
	}

}
