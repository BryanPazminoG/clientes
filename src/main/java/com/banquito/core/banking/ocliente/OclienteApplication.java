package com.banquito.core.banking.ocliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.banquito.core.clientes")
public class OclienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(OclienteApplication.class, args);
	}

}
