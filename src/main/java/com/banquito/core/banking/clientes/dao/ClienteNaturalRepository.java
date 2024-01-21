package com.banquito.core.banking.clientes.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.core.banking.clientes.domain.ClienteNatural;

public interface ClienteNaturalRepository extends MongoRepository<ClienteNatural, String> {
    
    ClienteNatural findByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);
    
}
