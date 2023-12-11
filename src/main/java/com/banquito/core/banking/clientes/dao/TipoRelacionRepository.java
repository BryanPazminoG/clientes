package com.banquito.core.banking.clientes.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.clientes.domain.TipoRelacion;


@Repository
public interface TipoRelacionRepository extends CrudRepository<TipoRelacion, String>{
    
    TipoRelacion findByNombre(String nombre);

}
