package com.banquito.core.banking.clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.clientes.domain.Cliente;
import com.banquito.core.banking.clientes.service.ClienteService;

@RestController
@RequestMapping
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente/getall")
    public List<Cliente> getall(){
        return clienteService.getAll();
    }

    @GetMapping("/cliente/{correo}")
    public ResponseEntity<List<Cliente>> getClientesByCorreo(@RequestParam String correo) {
        List<Cliente> clientes = clienteService.getByCorreo(correo);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/cliente/tipo")
    @ResponseBody
    public ResponseEntity<List<Cliente>> getByTipoIdentificacionAndNumeroIdentificacion(@RequestParam(name = "tipo") String tipo, @RequestParam(name = "numero") String numeroIdentificacion) {
        List<Cliente> clientes = clienteService.getByTipoIdentificacionAndNumeroIdentificacion(tipo, numeroIdentificacion);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/cliente/empresa")
    @ResponseBody
    public ResponseEntity<Cliente> getByNombreComercialOrRazonSocial(@RequestParam(name = "empresa") String empresa) {
        Cliente clientes = clienteService.getByNombreComercialOrRazonSocial(empresa);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }



}
