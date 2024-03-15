package com.banquito.core.clientes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banquito.core.clientes.domain.ClienteNatural;
import com.banquito.core.clientes.service.ClienteNaturalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:8081", "http://localhost:8082"})
@RequestMapping("/api/v1/naturales")
public class ClienteNaturalController {

    private final ClienteNaturalService clienteNaturalService;

    public ClienteNaturalController(ClienteNaturalService clienteNaturalService) {
        this.clienteNaturalService = clienteNaturalService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteNatural>> listarClientes() {
        log.info("Obteniendo listado de clientes");
        return ResponseEntity.ok(this.clienteNaturalService.listarTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteNatural> buscarPorId(@PathVariable(name = "id") String id) {
        log.info("Obteniendo cliente con ID: {}", id);
        try {
            return ResponseEntity.ok(this.clienteNaturalService.obtenerPorId(id));
        } catch(RuntimeException rte) {
            log.error("Error al obtener cliente por ID", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{tipoId}/{id}")
    public ResponseEntity<ClienteNatural> buscarPorIdentificacion(@PathVariable(name = "tipoId") String tipoId, @PathVariable(name = "id") String id) {
        log.info("Obteniendo cliente con TipoIdentificacion: {} y NumeroIdentificacion: {}", tipoId, id);
        try {
            return ResponseEntity.ok(this.clienteNaturalService.obtenerPorIdentificacion(tipoId, id));
        } catch(RuntimeException rte) {
            log.error("Error al obtener cliente por Identificacion", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody ClienteNatural cliente) {
        log.info("Se va a crear el cliente: {}", cliente);
        try {
            this.clienteNaturalService.crear(cliente);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al crear el cliente", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> actualizar(@RequestBody ClienteNatural cliente) {
        log.info("Se va a actualizar el cliente: {}", cliente);
        try {
            this.clienteNaturalService.actualizar(cliente);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al actualizar el cliente", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable(name = "id") String id) {
        log.info("Se va a desactivar el cliente con ID: {}", id);
        try {
            this.clienteNaturalService.desactivar(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al desactivar el cliente", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
