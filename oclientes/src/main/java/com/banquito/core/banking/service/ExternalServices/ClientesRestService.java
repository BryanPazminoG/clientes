package com.banquito.core.banking.service.ExternalServices;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientesRestService {

    private final RestTemplate restTemplate;

    public ResponseEntity<String> obtenerClientesNaturales(){
        String url = "http://localhost:8081/api/v1/clientes";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al obtener la información del producto desde el servicio externo");
        }
        return response;
    }


    @GetMapping("/{tipoId}/{id}")
    public ResponseEntity<String> buscarPorIdentificacion(@PathVariable(name = "tipoId") String tipoId, @PathVariable(name = "id") String id){
        String url = "http://localhost:8081/api/v1/clientes/"+ tipoId + "/"+ id;

        log.info(url);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error al obtener la información del producto desde el servicio externo");
        }
        return response;

    }
    
}
