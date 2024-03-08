package com.banquito.core.banking.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:8082", name = "empresa-service")
@Service
public interface ClienteService {

    @GetMapping("/api/v1/empresas/vive")
    public String pruebas(); 
    
}
