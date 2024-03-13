package com.banquito.core.banking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.service.ClienteNaturalService;
import com.banquito.core.banking.service.EmpresaService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteNaturalService oclienteNaturalService;
    private final EmpresaService oempresaService;


    public ClienteController(ClienteNaturalService oclienteNaturalService, EmpresaService oempresaService) {
        this.oclienteNaturalService = oclienteNaturalService;
        this.oempresaService = oempresaService;
    }

    @GetMapping("/empresas")
    public ResponseEntity<String> listarClientes(){
        return this.oempresaService.listarEmpresas();
    }

    @GetMapping("/naturales")
    public ResponseEntity<String> listarEmpresas(){
        return this.oclienteNaturalService.listarClientes();
    }

    @GetMapping("/naturales/{idCliente}")
    public ResponseEntity<String> obtenerPorId(@PathVariable(name = "idCliente") String idCliente){
        return this.oclienteNaturalService.obtenerPorIdCliente(idCliente);
    }


    @GetMapping("/{tipoId}/{id}")
    public ResponseEntity<String> obtenerPorIdentificacion(@PathVariable(name = "tipoId") String tipoId, @PathVariable(name = "id") String id){
        ResponseEntity<String> responseEntity;
        if ("RUC".equals(tipoId)) {
            responseEntity = this.oempresaService.obtenerPorTipoIndentificacion(id);
        } else {
            responseEntity = this.oclienteNaturalService.obtenerPorTipoIndentificacion(tipoId, id);
        }
        return responseEntity;
    }


    
    
}
