package com.banquito.core.banking.clientes.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.banquito.core.banking.clientes.dao.ClienteRepository;
import com.banquito.core.banking.clientes.dao.TipoRelacionRepository;
import com.banquito.core.banking.clientes.domain.Cliente;
import com.banquito.core.banking.clientes.domain.TipoRelacion;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    public static final String TIPO_CLIENTE_PERSONA = "NAT";
    private final ClienteRepository clienteRepository;
    private final TipoRelacionRepository tipoRelacionRepository;

    public ClienteService(ClienteRepository clienteRepository, TipoRelacionRepository tipoRelacionRepository) {
        this.clienteRepository = clienteRepository;
        this.tipoRelacionRepository = tipoRelacionRepository;
    }

    public Optional<Cliente> OptionalobtainById(Integer id){
        return this.clienteRepository.findById(id);
    }

    public Iterable<TipoRelacion> buscarTodo(){
        return this.tipoRelacionRepository.findAll();
    }

    @Transactional
    public TipoRelacion crearTipoRelacion(TipoRelacion tipoRelacion){
        try{
            return this.tipoRelacionRepository.save(tipoRelacion);
        } catch (Exception e){
            throw new CreacionException("Ocurrio un error al crear el tipo de Relacion"+tipoRelacion+", Error: "+e, e);
        }
    }

    @Transactional
    public Cliente crearPersona(Cliente cliente){
        try {
            if ("NAT".equals(cliente.getTipoCliente())){
                if(!"RUC".equals(cliente.getTipoIdentificacion())){
                    return this.clienteRepository.save(cliente);
                } else {
                    throw new RuntimeException("El tipo de identificacion es incorrecto.");
                }
            } else {
                throw new RuntimeException("El tipo de cliente es incorrecto");
            }
        } catch (Exception e) {
            throw new CreacionException("Error en creacion el cliente de tipo persona: "+cliente+", Error: "+e, e);
        }
    }


    public List<Cliente> obtenerPersonaPorApellidos(String apellidos){
        return this.clienteRepository.findByTipoClienteAndApellidosLikeOrderByApellidos(apellidos, apellidos);
    }

    public Cliente obtenerClientePorTipoYNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion){
        return this.clienteRepository.findByTipoIdentificacionAndNumeroIdentificacion(tipoIdentificacion, numeroIdentificacion);
    }

    public Cliente actualizar(Cliente personaUpdate) {
        try {
            Optional<Cliente> persona = clienteRepository.findById(personaUpdate.getCodigo());
            if (persona.isPresent()) {
                return clienteRepository.save(personaUpdate);
            } else {
                throw new RuntimeException(
                    "El Credito con numero de identificacion" + personaUpdate.getNumeroIdentificacion() + " no existe");
            }
        } catch (Exception e) {
            throw new CreacionException("Ocurrio un error al actualizar el Credito, error: " + e.getMessage(), e);
        }
    }



}
