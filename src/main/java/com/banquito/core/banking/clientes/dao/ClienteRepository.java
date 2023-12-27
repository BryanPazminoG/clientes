package com.banquito.core.banking.clientes.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.banking.clientes.domain.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer>{

    Cliente findByTipoIdentificacionAndNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion);


    List<Cliente> findByCorreoElectronico(String correoElectronico);
    List<Cliente> findByTelefonoOrderByCodigo(String telefono);
    
    List<Cliente> findByTipoClienteAndApellidosLikeOrderByApellidos(String tipoCliente, String apellido);
    //List<Cliente> findByTipoClienteAndRazonSocialLikeOrderByRazonSocial(String tipoCliente, String razonSocial);

}
