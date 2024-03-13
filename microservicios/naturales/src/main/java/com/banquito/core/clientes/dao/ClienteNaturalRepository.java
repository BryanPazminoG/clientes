package com.banquito.core.clientes.dao;

import com.banquito.core.clientes.domain.ClienteNatural;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteNaturalRepository extends MongoRepository<ClienteNatural, String> {

    ClienteNatural findByIdCliente(String idCliente);
    ClienteNatural findBfindById(String id);
    List<ClienteNatural> findByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);

}
