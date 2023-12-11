package com.banquito.core.banking.clientes.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.clientes.domain.ClientePersonaRelacion;

@Repository
public interface ClientePersonaRelacionRepository extends CrudRepository<ClientePersonaRelacion, Integer> {

    List<ClientePersonaRelacion> findByCodigoClientePersona(Integer codigoClientePersona);
    List<ClientePersonaRelacion> findByCodigoClienteEmpresa(Integer codigoClienteEmpresa);
    List<ClientePersonaRelacion> findByCodigoClienteEmpresaAndEstado(Integer codigoClienteEmpresa, String estado);
    List<ClientePersonaRelacion> findByCodigoClientePersonaAndEstado(Integer codigoClienteEmpresa, String estado);

    
}
