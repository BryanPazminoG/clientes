package com.banquito.core.clientes.service.ExternalServices;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpresasRestService {

    private final RestTemplate restTemplate;

    public ResponseEntity<String> obtenerEmpresas(){
        String url = "http://localhost:8082/api/v1/empresas";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al obtener la información del producto desde el servicio externo");
        }
        return response;
    }
    
}
