package com.banquito.core.banking.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.service.ClienteNaturalService;
import com.banquito.core.banking.service.EmpresaService;


@RestController
@RequestMapping("/api/v1/ocliente")
public class ClienteController {

    private final ClienteNaturalService oclienteNaturalService;
    private final EmpresaService oempresaService;


    public ClienteController(ClienteNaturalService oclienteNaturalService, EmpresaService oempresaService) {
        this.oclienteNaturalService = oclienteNaturalService;
        this.oempresaService = oempresaService;
    }

    @GetMapping("/empresas")
    public ResponseEntity<String> listarClientes(){
        return this.oempresaService.prueba();
    }

    @GetMapping("/clientes")
    public ResponseEntity<String> listarEmpresas(){
        return this.oclienteNaturalService.listarClientes();
    }

    @GetMapping("/clientes/{tipoId}/{id}")
    public ResponseEntity<String> obtenerPorIdentificacion(@PathVariable(name = "tipoId") String tipoId, @PathVariable(name = "id") String id){
        return this.oclienteNaturalService.obtenerPorTipoIndentificacionINumero(tipoId, id);
    }
    
}
