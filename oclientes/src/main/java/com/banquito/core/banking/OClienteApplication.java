package com.banquito.core.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;





@SpringBootApplication
@EnableFeignClients
public class OClienteApplication {
    public static void main(String[] args) {
        SpringApplication.run(OClienteApplication.class, args);
    }
}



