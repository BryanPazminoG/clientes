package com.banquito.core.banking.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.banquito.core.banking.service.ExternalServices.EmpresasRestService;

@Service
public class EmpresaRestService {

    private final RestTemplate restTemplate; 

    public EmpresaRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> listarEmEntity() {
        String url = "http://localhost:8081/api/v1/empresas/";
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> obtenerPorTipoIdentificacionYNumero(String tipo, String numero) {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/v1/clientes/")
                .pathSegment(tipo, numero)
                .build()
                .toString();
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<String> obtenerPorId(String id) {
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/api/v1/clientes/")
                .path("/{id}")
                .buildAndExpand(id)
                .toUriString();
        return restTemplate.getForEntity(url, String.class);
    }



    public EmpresaRestService(EmpresasRestService empresasRestService) {
        this.empresasRestService = empresasRestService;
    }

    public ResponseEntity<String> listarTodo() {
        return empresasRestService.obtenerEmpresas();
    }

    public ResponseEntity<String> obtenerPorId(String id) {
        return empresasRestService.obtenerEmpresaPorId(id);
    }

    public ResponseEntity<String> obtenerPorIdentificacion(String numeroIdentificacion) {
        return empresasRestService.obtenerEmpresaPorRUC(numeroIdentificacion);
    }

    public ResponseEntity<String> crear(Empresa empresa) {
        return empresasRestService.crearEmpresa(empresa);
    }

    public ResponseEntity<String> actualizar(Empresa empresa) {
        return empresasRestService.actualizarEmpresa(empresa);
    }

    public ResponseEntity<String> quitarMiembroEmpresa(String idEmpresa, String idMiembro) {
        return empresasRestService.quitarMiembroDeEmpresa(idEmpresa, idMiembro);
    }

    public ResponseEntity<String> desactivar(String idEmpresa) {
        return empresasRestService.desactivarEmpresa(idEmpresa);
    }

}
