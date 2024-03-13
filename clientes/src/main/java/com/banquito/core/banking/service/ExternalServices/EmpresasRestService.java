package com.banquito.core.banking.service.ExternalServices;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestTemplate;

@Service
public class EmpresasRestService {


    private final RestTemplate restTemplate; 

    public EmpresasRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public ResponseEntity<String> listarEmpresas() {
        String url = "http://localhost:8081/api/v1/empresas";
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> obtenerPorId(String id) {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/v1/empresas")
                .path("/{id}")
                .buildAndExpand(id)
                .toUriString();
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> obtenerPorIdentificacion(String numeroIdentificacion) {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/v1/empresas")
                .path("/ruc/{numeroIdentificacion}")
                .buildAndExpand(numeroIdentificacion)
                .toUriString();
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> desactivar(String idCliente) {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/v1/empresas")
                .path("/desactivar/{idCliente}")
                .buildAndExpand(idCliente)
                .toUriString();
        restTemplate.put(url, null);
        return ResponseEntity.noContent().build();
    }
}
