package com.banquito.core.empresas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.empresas.domain.Empresa;
import com.banquito.core.empresas.service.EmpresaService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarClientes() {
        log.info("Obteniendo listado de clientes");
        return ResponseEntity.ok(this.empresaService.listarTodo());
    }
    
    @GetMapping("/vive")
    public String pruebas() {
        log.info("Obteniendo listado de clientes");
        return "Vive x2";
    }
    
    
}
