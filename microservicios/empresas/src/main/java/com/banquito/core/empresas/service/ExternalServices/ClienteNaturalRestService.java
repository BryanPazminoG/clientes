package com.banquito.core.empresas.service.ExternalServices;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClienteNaturalRestService {

    private final RestTemplate restTemplate; 

    public ClienteNaturalRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> obtenerPorId(String id) {
        String url = "http://naturales:8081/api/v1/naturales/" + id;
    
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al obtener la información del cliente natural desde el servicio externo");
        }
    
        return response;
    }
    
    
    
}
