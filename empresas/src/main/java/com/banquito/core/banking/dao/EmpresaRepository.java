package com.banquito.core.banking.dao;

import com.banquito.core.banking.domain.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {

    Empresa findByIdCliente(String idCliente);

    List<Empresa> findByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);
}
