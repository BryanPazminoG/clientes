package com.banquito.core.banking.clientes.controller;


import java.util.Optional;


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

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/guardar/persona")
    public ResponseEntity<Cliente> GuardarPersona(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.crearPersona(cliente), HttpStatus.OK);
    }

    @PostMapping("/guardar/empresa")
    public ResponseEntity<Cliente> GuardarEmpresa(@RequestBody Cliente empresa) {
        return new ResponseEntity<>(clienteService.crearEmpresa(empresa), HttpStatus.OK);
    }

    @GetMapping("/buscar-cliente")
    public ResponseEntity<Cliente> buscarClientePorId(@RequestParam("id") Integer codigo) {
        if (clienteService != null) {
            Optional<Cliente> optionalCliente = clienteService.buscarClientePorId(codigo);

            if (optionalCliente.isPresent()) {
                Cliente cliente = optionalCliente.get();
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @GetMapping("/guardar/relacion-cliente")
    public ResponseEntity<ClientePersonaRelacion> crearRelacionClientePersona(
            @RequestParam("codigoEmpresa") Integer empresa,
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
