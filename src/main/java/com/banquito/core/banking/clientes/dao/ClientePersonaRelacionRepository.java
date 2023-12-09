package com.banquito.core.banking.clientes.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.banquito.core.banking.clientes.domain.ClientePersonaRelacion;

public interface ClientePersonaRelacionRepository extends CrudRepository<ClientePersonaRelacion, Integer> {

    ClientePersonaRelacion findByCodigoClientePersona(Integer codigoClientePersona);
    List<ClientePersonaRelacion> findByCodigoClienteEmpresa(Integer codigoClienteEmpresa);
    List<ClientePersonaRelacion> findByCodigoClienteEmpresaAndEstado(Integer codigoClienteEmpresa, String estado);
    List<ClientePersonaRelacion> findByFechaInicioBetweenAndCodigoClientePersona(Date fechaInicioDesde, Date fechaInicioHasta, Integer codigoClientePersona);
}
