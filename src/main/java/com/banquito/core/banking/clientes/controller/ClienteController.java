package com.banquito.core.banking.clientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.clientes.domain.Cliente;
import com.banquito.core.banking.clientes.domain.ClientePersonaRelacion;
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
    public ResponseEntity<Cliente> buscarClientesPorParametros(@RequestParam("tipo") String tipo, @RequestParam("numero") String numero) {
        return new ResponseEntity<>(clienteService.obtenerClientePorTipoYNumeroIdentificacion(tipo, numero), HttpStatus.OK);
    }

    @PutMapping("/actualizar/persona")
    public ResponseEntity<Cliente> Update(@RequestBody Cliente persona) {
        return new ResponseEntity<>(clienteService.actualizar(persona), HttpStatus.OK);
    }

    @PostMapping("/guardar/relacion-cliente")
    public ResponseEntity<ClientePersonaRelacion> crearRelacionClientePersona(
            @RequestBody Cliente empresa,
            @RequestParam("tipoIdentificacionPersona") String tipoIdentificacionPersona,
            @RequestParam("numeroIdentificacionPersona") String numeroIdentificacionPersona,
            @RequestParam("codigoRelacion") String codigoRelacion
    ) {
        ClientePersonaRelacion relacion = clienteService.CrearClientePersonaRelacion(
                empresa,
                tipoIdentificacionPersona,
                numeroIdentificacionPersona,
                codigoRelacion
        );

        return new ResponseEntity<>(relacion, HttpStatus.OK);
    }

}
