package com.banquito.core.banking.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.service.ExternalServices.ClientesRestService;

@Service
public class OClienteService {

    private ClientesRestService clientesRestService;

    public OClienteService(ClientesRestService clientesRestService) {
        this.clientesRestService = clientesRestService;
    }

    public ResponseEntity<String> listarClientes(){
        return this.clientesRestService.obtenerClientesNaturales();
    }

    public ResponseEntity<String> obtenerPorTipoIndentificacionINumero(String tipo, String numero){
        return this.clientesRestService.buscarPorIdentificacion(tipo, numero);
    }
    
}
