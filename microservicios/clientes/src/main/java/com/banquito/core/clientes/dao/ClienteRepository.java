package com.banquito.core.clientes.dao;

import com.banquito.core.clientes.domain.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

    Cliente findByIdCliente(String idCliente);

}
