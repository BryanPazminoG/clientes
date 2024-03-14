package com.banquito.core.banking.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.service.ExternalServices.EmpresasRestService;

@Service
public class EmpresaService {

    private final EmpresasRestService empresasRestService;

    public EmpresaService(EmpresasRestService empresasRestService) {
        this.empresasRestService = empresasRestService;
    }

    public ResponseEntity<String> listarEmpresas(){
        return this.empresasRestService.listarEmpresas();
    }

    public ResponseEntity<String> obtenerPorTipoIndentificacion(String numero){
        return this.empresasRestService.obtenerPorIdentificacion(numero);
    }

    public ResponseEntity<String> obtenerPorIdEmpresa(String idEmpresa){
        return this.empresasRestService.obtenerPorId(idEmpresa);
    }

    public ResponseEntity<String> crearEmpresas(String json){
        return this.empresasRestService.crearEmpresas(json);
    }
    
    public ResponseEntity<String> actualizarEmpresas(String json){
        return this.empresasRestService.actualizarEmpresas(json);
    }
    

    
}
