package com.banquito.core.banking.clientes.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.clientes.domain.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

    List<Cliente> findByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);
    List<Cliente> findByCorreoElectronico(String correoElectronico);
    List<Cliente> findByTelefono(String telefono);
    
    Cliente findByNombreComercialOrRazonSocial(String nombreComercial, String razonSocial);
    

}
