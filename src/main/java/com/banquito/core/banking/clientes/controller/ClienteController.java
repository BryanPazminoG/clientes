package com.banquito.core.banking.clientes.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.clientes.domain.Cliente;
import com.banquito.core.banking.clientes.service.ClienteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/guardar/persona")
    public ResponseEntity<Cliente> GuardarPersona(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.crearPersona(cliente), HttpStatus.OK);
    }

    @PostMapping("/guardar/empresa")
    public ResponseEntity<Cliente> GuardarEmpresa(@RequestBody Cliente empresa) {
        return new ResponseEntity<>(clienteService.crearEmpresa(empresa), HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Cliente> buscarClientesPorParametros(@RequestParam("tipo") String tipo,
            @RequestParam("numero") String numero) {
        return new ResponseEntity<>(clienteService.obtenerClientePorTipoYNumeroIdentificacion(tipo, numero),
                HttpStatus.OK);
    }

    @PutMapping("/actualizar/persona")
    public ResponseEntity<Cliente> Update(@RequestBody Cliente persona) {
        return new ResponseEntity<>(clienteService.actualizar(persona), HttpStatus.OK);
    }

    @GetMapping("/buscarporcodigo/{codigo}")
    public ResponseEntity<Cliente> buscarClientePorCodigo(@PathVariable Integer codigo) {
        Optional<Cliente> cliente = clienteService.obtenerClientePorCodCliente(codigo);

        if (cliente.isPresent()) {
            return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
