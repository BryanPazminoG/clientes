package com.banquito.core.banking.clientes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banquito.core.banking.clientes.dao.ClienteRepository;
import com.banquito.core.banking.clientes.domain.Cliente;
import com.banquito.core.banking.clientes.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAll() { 
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public List<Cliente> getByCorreo(String correo) {
        return (List<Cliente>) clienteRepository.findByCorreoElectronico(correo);
    }

    @Override
    public List<Cliente> getByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion){
        return (List<Cliente>) clienteRepository.findByTipoIdentificacionAndNumeroIdentificacion(tipoIdentificacion, numeroIdentificacion);
    }

    @Override
    public Cliente getByNombreComercialOrRazonSocial(String empresa){
        return (Cliente) clienteRepository.findByNombreComercialOrRazonSocial(empresa, empresa);
    }
    
}
