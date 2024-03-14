package com.banquito.core.banking.service.ExternalServices;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class ClienteNaturalRestService {
    private final RestTemplate restTemplate; 
    private final HttpHeaders headers;

    public ClienteNaturalRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
    
    public ResponseEntity<String> obtenerClientesNaturales(){
        String url = "http://localhost:8081/api/v1/naturales";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al obtener la información del producto desde el servicio externo");
        }
        return response;
    }

    @GetMapping("/{tipoId}/{id}")
    public ResponseEntity<String> buscarPorIdentificacion(@PathVariable(name = "tipoId") String tipoId, @PathVariable(name = "id") String id){
        String url = "http://localhost:8081/api/v1/naturales/"+ tipoId + "/"+ id;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al obtener la información del producto desde el servicio externo");
        }
        return response;
    }

    public ResponseEntity<String> obtenerPorTipoIdentificacionYNumero(String tipo, String numero) {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/v1/naturales/")
                .pathSegment(tipo, numero)
                .build()
                .toString();
        return restTemplate.getForEntity(url, String.class);
    }
    
    public ResponseEntity<String> obtenerPorId(String id) {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/v1/naturales/")
                .path("/{id}")
                .buildAndExpand(id)
                .toUriString();
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> desactivar(String id) {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/v1/naturales/")
                .path("/desactivar/{id}")
                .buildAndExpand(id)
                .toUriString();
        restTemplate.put(url, null);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<String> crearClienteNatural(String clienteJson) {
        String url = "http://localhost:8081/api/v1/naturales";
        HttpEntity<String> requestEntity = new HttpEntity<>(clienteJson, headers);
        return restTemplate.postForEntity(url, requestEntity, String.class);
    }

    public ResponseEntity<String> actualizarClienteNatural(String clienteJson) {
        try {
            String url = "http://localhost:8081/api/v1/naturales";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(clienteJson, headers);
            restTemplate.put(url, requestEntity);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud: " + e.getMessage());
        }
    }



    
}
