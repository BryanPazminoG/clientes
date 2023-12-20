package com.banquito.core.banking.clientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.clientes.domain.Cliente;
import com.banquito.core.banking.clientes.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    // @GetMapping("/listar")
    // public ResponseEntityIterable<><Cliente> Listar(@RequestBody Cliente cliente) {
    //     return new ResponseEntity<Iterable<>>(clienteService.buscarTodo(), HttpStatus.OK);
    // }

    @PostMapping("/save")
    public ResponseEntity<Cliente> Save(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.crearPersona(cliente), HttpStatus.OK);
    }




}
