package com.banquito.core.banking.clientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.clientes.domain.TipoRelacion;
import com.banquito.core.banking.clientes.service.TipoRelacionService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tipo-relacion")
public class TipoRelacionController {
    @Autowired
    private TipoRelacionService tipoRelacionService;

    @GetMapping("/todos")
    public Iterable<TipoRelacion> obtenerTodosLosTiposDeRelacion() {
        return tipoRelacionService.buscarTodo();
    }

}
