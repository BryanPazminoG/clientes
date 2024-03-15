package com.banquito.core.banking.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.service.ClienteNaturalService;
import com.banquito.core.banking.service.EmpresaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteNaturalService oclienteNaturalService;
    private final EmpresaService oempresaService;


    public ClienteController(ClienteNaturalService oclienteNaturalService, EmpresaService oempresaService) {
        this.oclienteNaturalService = oclienteNaturalService;
        this.oempresaService = oempresaService;
    }

    // CLIENTES EMPRESAS
    @GetMapping("/empresas")
    public ResponseEntity<String> listarClientes(){
        return this.oempresaService.listarEmpresas();
    }

    @GetMapping("/empresas/{idEmpresa}")
    public ResponseEntity<String> obtenerPorIdEmpresa(@PathVariable(name = "idEmpresa") String idCliente){
        return this.oempresaService.obtenerPorIdEmpresa(idCliente);
    }

    @PostMapping("/empresas")
    public ResponseEntity<String> crearEmpresas(@RequestBody String empresasJson) {
        return this.oempresaService.crearEmpresas(empresasJson);
    }

    @PutMapping("/empresas")
    public ResponseEntity<String> actualizarEmpresas(@RequestBody String empresasJson) {
        return this.oempresaService.actualizarEmpresas(empresasJson);
    }

    // CLIENTES NATURALES
    @GetMapping("/naturales")
    public ResponseEntity<String> listarEmpresas(){
        return this.oclienteNaturalService.listarClientes();
    }

    @GetMapping("/naturales/{idCliente}")
    public ResponseEntity<String> obtenerPorIdCliente(@PathVariable(name = "idCliente") String idCliente){
        return this.oclienteNaturalService.obtenerPorIdCliente(idCliente);
    }

    @PostMapping("/naturales")
    public ResponseEntity<String> crearClienteNatural(@RequestBody String clienteJson) {
        return this.oclienteNaturalService.crearClienteNatural(clienteJson);
    }

    @PutMapping("/naturales")
    public ResponseEntity<String> actualizarClientesNaturales(@RequestBody String naturalesJson) {
        return this.oclienteNaturalService.actualizarClienteNatural(naturalesJson);
    }


    // CLIENTES NATURALES Y EMPRESAS
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
