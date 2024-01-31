package com.banquito.core.banking.clientes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.clientes.domain.Cliente;
import com.banquito.core.banking.clientes.service.ClienteService;

import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> buscarTodos() {
        log.info("Obteniendo listado de todos los clientes");
        return ResponseEntity.ok(this.clienteService.listarTodo());
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathParam("id") String id) {
        log.info("Obteniendo cliente por ID: {}", id);
        try {
            return ResponseEntity.ok(this.clienteService.obtenerPorId(id));
        } catch (RuntimeException rte) {
            log.error("Error al obtener cliente", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/{tipoId}/{id}")
    public ResponseEntity<Cliente> buscarPorIdentificacion(@RequestParam("tipoId") String tipoId,
            @RequestParam("id") String id) {
        log.info("Obteniendo cliente por TipoId e ID: ({},{})", tipoId, id);
        try {
            return ResponseEntity.ok(this.clienteService.obtenerPorIdentificacion(tipoId, id));
        } catch (RuntimeException rte) {
            log.error("Error al obtener cliente", rte);
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<Void> crearCliente(Cliente cliente) {
        log.info("Va a crear el cliente: {}", cliente);
        try {
            this.clienteService.crear(cliente);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException rte) {
            log.error("Error al crear el cliente", rte);
            return ResponseEntity.badRequest().build();
        }
    }
}
