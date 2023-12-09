package com.banquito.core.banking.clientes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.clientes.dao.TipoRelacionRepository;
import com.banquito.core.banking.clientes.domain.TipoRelacion;
import com.banquito.core.banking.clientes.service.TipoRelacionService;

@Service
public class TipoRelacionServiceImpl implements TipoRelacionService{

    @Autowired
    private TipoRelacionRepository tipoRelacionRepository;

    @Override
    public List<TipoRelacion> getAll() { 
        return (List<TipoRelacion>) tipoRelacionRepository.findAll();
    }
}
