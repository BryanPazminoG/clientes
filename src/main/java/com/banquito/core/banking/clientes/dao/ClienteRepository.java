package com.banquito.core.banking.clientes.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.banquito.core.banking.clientes.domain.Cliente;


public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

    Cliente findByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);
    Cliente findByCorreoElectronico(String correoElectronico);
    List<Cliente> findByTelefono(String telefono);

    // NUEVOS
    Cliente findByNumeroIdentificacionOrCorreoElectronico(String tipoIdentificacion, String correoElectronico);
    Cliente findByNombreComercialOrRazonSocial(String nombreComercial, String razonSocial);
    List<Cliente> findByTipoClienteAndFechaNacimientoBetween(String tipoCliente, LocalDate fechaInicio, LocalDate fechaFin);


}
