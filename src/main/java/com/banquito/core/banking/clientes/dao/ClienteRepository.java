package com.banquito.core.banking.clientes.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.banquito.core.banking.clientes.domain.Cliente;


public interface ClienteRepository extends CrudRepository<ClienteRepository, Integer>{

    Cliente findByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);
    Cliente findByCorreoElectronico(String correoElectronico);
    List<Cliente> findByTelefono(String telefono);

    // NUEVOS
    Cliente findByNumeroIdentificacionOrCorreoElectronico(String tipoIdentificacion, String correoElectronico);
    

}
