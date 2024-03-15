package com.banquito.core.empresas.dao;

import com.banquito.core.empresas.domain.Empresa;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmpresaRepository extends MongoRepository<Empresa, String> {

    Empresa findByCodEmpresa(String codEmpresa);

    List<Empresa> findByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);
}
