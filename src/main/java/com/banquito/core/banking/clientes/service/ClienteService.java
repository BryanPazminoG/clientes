package com.banquito.core.banking.clientes.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.banquito.core.banking.clientes.dao.ClientePersonaRelacionRepository;
import com.banquito.core.banking.clientes.dao.ClienteRepository;
import com.banquito.core.banking.clientes.dao.TipoRelacionRepository;
import com.banquito.core.banking.clientes.domain.Cliente;
import com.banquito.core.banking.clientes.domain.ClientePersonaRelacion;
import com.banquito.core.banking.clientes.domain.TipoRelacion;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {

    public static final String TIPO_CLIENTE_PERSONA = "NAT";
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private final ClienteRepository clienteRepository;
    private final TipoRelacionRepository tipoRelacionRepository;
    private final ClientePersonaRelacionRepository clientePersonaRelacionRepository;

    public ClienteService(ClienteRepository clienteRepository, TipoRelacionRepository tipoRelacionRepository, ClientePersonaRelacionRepository clientePersonaRelacionRepository) {
        this.clienteRepository = clienteRepository;
        this.tipoRelacionRepository = tipoRelacionRepository;
        this.clientePersonaRelacionRepository = clientePersonaRelacionRepository;
    }

    public Optional<Cliente> buscarClientePorId(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente obtenerClientePorTipoYNumeroIdentificacion(String tipoIdentificacion, String numeroIdentificacion){
        return this.clienteRepository.findByTipoIdentificacionAndNumeroIdentificacion(tipoIdentificacion, numeroIdentificacion);
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


    @Transactional
    public Cliente crearEmpresa(Cliente empresa){
    try {
        if ("JUR".equals(empresa.getTipoCliente())){
            if("RUC".equals(empresa.getTipoIdentificacion())){
                return this.clienteRepository.save(empresa);
            } else {
                throw new RuntimeException("El tipo de identificacion es incorrecto.");
            }
        } else {
            throw new RuntimeException("El tipo de empresa es incorrecto");
        }
    } catch (Exception e) {
        throw new CreacionException("Error en creacion de la empresa: "+empresa+", Error: "+e, e);
    }
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

    @Transactional
    public ClientePersonaRelacion CrearClientePersonaRelacion(Integer codigoEmpresa, String tipoIdentificacionPersona, String numeroIdentificacionPersona, String codigoRelacion){

        
        try {
    
            Cliente persona = obtenerClientePorTipoYNumeroIdentificacion(tipoIdentificacionPersona, numeroIdentificacionPersona);

            if (persona != null) {

                ClientePersonaRelacion relacion = new ClientePersonaRelacion();
                relacion.setCodigoTipoRelacion(codigoRelacion);
                relacion.setCodigoClienteEmpresa(codigoEmpresa);
                relacion.setCodigoClientePersona(persona.getCodigo());
                relacion.setEstado("ACT");

                LocalDate fechaEspecifica = LocalDate.of(2023, 12, 29);
                Date fechaSQL = Date.valueOf(fechaEspecifica);

                relacion.setFechaInicio(fechaSQL);
                relacion.setFechaFin(null);
                relacion.setFechaModificacion(timestamp);
                return clientePersonaRelacionRepository.save(relacion);
                
            } else {
                throw new RuntimeException("No se encontró la persona con la información proporcionada.");
            }

        } catch (Exception e) {
            throw new CreacionException("Error al crear la relación entre clientes: " + e.getMessage(), e);
        }

    } 




}
