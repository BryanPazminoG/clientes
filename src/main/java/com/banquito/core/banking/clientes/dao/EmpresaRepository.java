package com.banquito.core.banking.clientes.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.banquito.core.banking.clientes.domain.Empresa;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {

    Empresa findByIdCliente(String idCliente);

    List<Empresa> findByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);
}
