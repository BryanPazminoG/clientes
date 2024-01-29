package com.banquito.core.banking.clientes.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.core.banking.clientes.domain.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

    Cliente findByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);

    Cliente findByCorreoElectronico(String correoElectronico);

    List<Cliente> findByTipoClienteOrderByApellidos(String tipoCliente);

    List<Cliente> findByTipoClienteAndRazonSocialLikeOrderByRazonSocial(String tipoCliente, String razonSocial);
}