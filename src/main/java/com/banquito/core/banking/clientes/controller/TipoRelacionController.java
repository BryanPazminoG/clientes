package com.banquito.core.banking.clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.clientes.domain.TipoRelacion;
import com.banquito.core.banking.clientes.service.TipoRelacionService;

@RestController
@RequestMapping
public class TipoRelacionController {

    @Autowired
    private TipoRelacionService tipoRelacionService;

    @GetMapping("/tiposrelacion")
    public List<TipoRelacion> getAll(){
        return tipoRelacionService.getAll();
    }

}
