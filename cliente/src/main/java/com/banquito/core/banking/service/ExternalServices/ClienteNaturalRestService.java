package com.banquito.core.banking.service.ExternalServices;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ClienteNaturalRestService {
    private final RestTemplate restTemplate; 

    public ClienteNaturalRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
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

    public ResponseEntity<String> listarnaturales() {
        String url = "http://localhost:8081/api/v1/naturales/";
        return restTemplate.getForEntity(url, String.class);
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
}
