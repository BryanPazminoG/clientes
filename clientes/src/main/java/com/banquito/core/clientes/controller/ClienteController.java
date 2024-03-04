package com.banquito.core.clientes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.banquito.core.clientes.domain.Cliente;
import com.banquito.core.clientes.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        //log.info("Obteniendo listado de clientes");
        return ResponseEntity.ok(this.clienteService.listarTodo());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable(name = "id") String id) {
        //log.info("Obteniendo cliente con ID: {}", id);
        try {
            return ResponseEntity.ok(this.clienteService.obtenerPorId(id));
        } catch(RuntimeException rte) {
            //log.error("Error al obtener cliente por ID", rte);
            return ResponseEntity.notFound().build();
        }
    }

    
}
