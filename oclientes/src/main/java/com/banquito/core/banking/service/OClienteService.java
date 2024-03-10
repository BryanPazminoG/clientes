package com.banquito.core.banking.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.service.ExternalServices.ClientesRestService;

@Service
public class OClienteService {

    private OClienteService oClientesService;

    public OClienteService(ClientesRestService clientesRestService) {
        this.oClientesService = oClientesService;
    }

    public ResponseEntity<String> listarClientes(){
        return this.oClientesService.obtenerClientesNaturales();
    }

    public ResponseEntity<String> obtenerPorTipoIndentificacionINumero(String tipo, String numero){
        return this.oClientesService.buscarPorIdentificacion(tipo, numero);
    }
    
}