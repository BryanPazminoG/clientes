package com.banquito.core.banking.clientes.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.core.banking.clientes.domain.ClienteJuridico;

public interface ClienteJuridicoRepository extends MongoRepository<ClienteJuridico, String> {

    ClienteJuridico findByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);
    
}
