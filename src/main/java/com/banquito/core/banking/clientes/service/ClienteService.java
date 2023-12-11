package com.banquito.core.banking.clientes.service;

import java.util.List;

import com.banquito.core.banking.clientes.domain.Cliente;

public interface ClienteService {

    List<Cliente> getAll();

    List<Cliente> getByCorreo(String correo);

    List<Cliente> getByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);

    Cliente getByNombreComercialOrRazonSocial(String empresa);


}
