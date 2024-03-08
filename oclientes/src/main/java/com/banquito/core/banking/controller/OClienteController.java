package com.banquito.core.banking.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.service.OClienteService;
import com.banquito.core.banking.service.OEmpresaService;


@RestController
@RequestMapping("/api/v1/ocliente")
public class OClienteController {

    private final OClienteService oclienteService;
    private final OEmpresaService oempresaService;


    public OClienteController(OClienteService oclienteService, OEmpresaService oempresaService) {
        this.oclienteService = oclienteService;
        this.oempresaService = oempresaService;
    }

    @GetMapping("/empresas")
    public ResponseEntity<String> listarClientes(){
        return this.oempresaService.prueba();
    }

    @GetMapping("/clientes")
    public ResponseEntity<String> listarEmpresas(){
        return this.oclienteService.listarClientes();
    }

    @GetMapping("/clientes/{tipoId}/{id}")
    public ResponseEntity<String> obtenerPorIdentificacion(@PathVariable(name = "tipoId") String tipoId, @PathVariable(name = "id") String id){
        return this.oclienteService.obtenerPorTipoIndentificacionINumero(tipoId, id);
    }
    
}
