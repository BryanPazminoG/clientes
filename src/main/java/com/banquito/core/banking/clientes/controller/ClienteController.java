package com.banquito.core.banking.clientes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.clientes.dto.ClienteDTO;
import com.banquito.core.banking.clientes.service.ClienteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        log.info("Obteniendo listado de clientes");
        return ResponseEntity.ok(this.clienteService.listarTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable(name = "id") String id) {
        log.info("Obteniendo cliente con ID: {}", id);
        try {
            return ResponseEntity.ok(this.clienteService.obtenerPorId(id));
        } catch(RuntimeException rte) {
            log.error("Error al obtener cliente por ID", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{tipoId}/{id}")
    public ResponseEntity<ClienteDTO> buscarPorIdentificacion(@PathVariable(name = "tipoId") String tipoId, @PathVariable(name = "id") String id) {
        log.info("Obteniendo cliente con TipoIdentificacion: {} y NumeroIdentificacion: {}", tipoId, id);
        try {
            return ResponseEntity.ok(this.clienteService.obtenerPorIdentificacion(tipoId, id));
        } catch(RuntimeException rte) {
            log.error("Error al obtener cliente por Identificacion", rte);
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody ClienteDTO cliente) {
        log.info("Se va a crear el cliente: {}", cliente);
        try {
            this.clienteService.crear(cliente);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al crear el cliente", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> actualizar(@RequestBody ClienteDTO cliente) {
        log.info("Se va a actualizar el cliente: {}", cliente);
        try {
            this.clienteService.actualizar(cliente);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al actualizar el cliente", rte);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> desactivar(@PathVariable(name = "id") String id) {
        log.info("Se va a desactivar el cliente con ID: {}", id);
        try {
            this.clienteService.desactivar(id);
            return ResponseEntity.noContent().build();
        } catch(RuntimeException rte) {
            log.error("Error al desactivar el cliente", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
