package com.banquito.core.banking.service.ExternalServices;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EmpresasRestService {
    
    private final RestTemplate restTemplate;
    private final HttpHeaders headers; 

    public EmpresasRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
    
    public ResponseEntity<String> listarEmpresas() {
        String url = "http://empresas:8082/api/v1/empresas";
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> obtenerPorId(String id) {
        String url = UriComponentsBuilder.fromHttpUrl("http://empresas:8082/api/v1/empresas")
                .path("/{id}")
                .buildAndExpand(id)
                .toUriString();
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> obtenerPorIdentificacion(String numeroIdentificacion) {
        String url = UriComponentsBuilder.fromHttpUrl("http://empresas:8082/api/v1/empresas")
                .path("/RUC/{numeroIdentificacion}")
                .buildAndExpand(numeroIdentificacion)
                .toUriString();
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> desactivar(String idCliente) {
        String url = UriComponentsBuilder.fromHttpUrl("http://empresas:8082/api/v1/empresas")
                .path("/desactivar/{idCliente}")
                .buildAndExpand(idCliente)
                .toUriString();
        restTemplate.put(url, null);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<String> crearEmpresas(String empresasJson) {
        String url = "http://empresas:8082/api/v1/empresas";
        HttpEntity<String> requestEntity = new HttpEntity<>(empresasJson, headers);
        return restTemplate.postForEntity(url, requestEntity, String.class);
    }


    public ResponseEntity<String> actualizarEmpresas(String empresaJson) {
        try {
            String url = "http://empresas:8082/api/v1/empresas";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(empresaJson, headers);
            restTemplate.put(url, requestEntity);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }
}

