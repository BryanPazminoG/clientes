package com.banquito.core.empresas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        log.info("Obteniendo listado de clientes juridicos");
        return ResponseEntity.ok(this.empresaService.listarTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable(name = "id") String id) {
        log.info("Obteniendo cliente juridico con ID: {}", id);
        try {
            return ResponseEntity.ok(this.empresaService.obtenerPorId(id));
        } catch(RuntimeException rte) {
            log.error("Error al obtener cliente juridico por ID", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/RUC/{id}")
    public ResponseEntity<Empresa> buscarPorIdentificacion(@PathVariable(name = "id") String id) {
        log.info("Obteniendo cliente juridico con TipoIdentificacion: RUC y NumeroIdentificacion: {}", id);
        try {
            return ResponseEntity.ok(this.empresaService.obtenerPorIdentificacion(id));
        } catch(RuntimeException rte) {
            log.error("Error al obtener cliente juridico por Identificacion", rte);
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody Empresa empresa) {
        log.info("Se va a crear el cliente juridico: {}", empresa);
        try {
            this.empresaService.crear(empresa);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al crear el cliente juridico", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> actualizar(@RequestBody Empresa empresa) {
        log.info("Se va a actualizar el cliente juridico: {}", empresa);
        try {
            this.empresaService.actualizar(empresa);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al actualizar el cliente juridico", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{idEmpresa}/miembros/{idMiembro}")
    public ResponseEntity<Void> quitarMiembroEmpresa(@PathVariable(name = "idEmpresa") String idEmpresa, @PathVariable(name = "idMiembro") String idMiembro) {
        log.info("Se va a desactivar miembro: {} de cliente juridico: {}", idMiembro, idEmpresa);
        try {
            this.empresaService.quitarMiembroEmpresa(idEmpresa, idMiembro);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al desactivar miembro de cliente juridico", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable(name = "id") String id) {
        log.info("Se va a desactivar el cliente juridico con ID: {}", id);
        try {
            this.empresaService.desactivar(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al desactivar el cliente juridico", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}

