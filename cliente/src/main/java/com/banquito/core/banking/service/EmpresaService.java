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
}
