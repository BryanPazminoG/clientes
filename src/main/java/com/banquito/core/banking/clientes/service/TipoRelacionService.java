package com.banquito.core.banking.clientes.service;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.clientes.dao.TipoRelacionRepository;
import com.banquito.core.banking.clientes.domain.TipoRelacion;

@Service
public class TipoRelacionService {

    private final TipoRelacionRepository tipoRelacionRepository;

    
    
    public TipoRelacionService(TipoRelacionRepository tipoRelacionRepository) {
        this.tipoRelacionRepository = tipoRelacionRepository;
    }


    public Iterable<TipoRelacion> buscarTodo(){
        return this.tipoRelacionRepository.findAll();
    }


}
