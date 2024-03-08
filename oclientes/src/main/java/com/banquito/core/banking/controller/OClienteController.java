package com.banquito.core.banking.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.banking.service.OClienteService;



@RestController
@RequestMapping("/ocliente")
public class OClienteController {

    private final OClienteService oclienteService;
    public OClienteController(OClienteService oclienteService) {
        this.oclienteService = oclienteService;
    }

    @GetMapping
    public String pruebas(){
        return this.oclienteService.prueba();
    }


    
}
