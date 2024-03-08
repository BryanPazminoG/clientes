package com.banquito.core.clientes.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.core.clientes.dao.ClienteRepository;
import com.banquito.core.clientes.domain.Cliente;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodo() {
        log.info("Se va a obtener todos los clientes naturales");
        List<Cliente> dtos = new ArrayList<>();
        for (Cliente cliente : this.clienteRepository.findAll()) {
            if ("ACT".equals(cliente.getEstado())) {
            dtos.add(cliente);
            }
        }
        return dtos;
    }

    public Cliente obtenerPorIdentificacion(String tipoIdentificacion, String numeroIdentificacion) {
        log.info("Se va a obtener cliente por TipoIdentificacion: {} y NumeroIdentificacion: {}", tipoIdentificacion,numeroIdentificacion);
        List<Cliente> clientes = this.clienteRepository.findByTipoIdentificacionAndNumeroIdentificacion(tipoIdentificacion, numeroIdentificacion);
        if (clientes != null && !clientes.isEmpty()) {
            if ("ACT".equals(clientes.get(0).getEstado())) {
                log.debug("Cliente obtenido: {}", clientes.get(0));
                return clientes.get(0);
            } else {
                throw new RuntimeException("Cliente con TipoIdentificacion: " + tipoIdentificacion
                        + " y NumeroIdentificacion: " + numeroIdentificacion + " no se encuentra activo");
            }
        } else {
            throw new RuntimeException("No existe el cliente con TipoIdentificacion: " + tipoIdentificacion
                    + " y NumeroIdentificacion: " + numeroIdentificacion);
        }
    }

    public Cliente obtenerPorId(String id) {
        log.info("Se va a obtener el cliente con ID: {}", id);
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        if (cliente != null) {
            if ("ACT".equals(cliente.get().getEstado())) {
                log.debug("Cliente obtenido: {}", cliente);
                return cliente.get();
            } else {
                throw new RuntimeException("Cliente con ID: " + id + " no se encuentra activo");
            }
        } else {
            throw new RuntimeException("No existe el cliente con el ID: " + id);
        }
    }
    
    @Transactional
    public void crear(Cliente cliente) {
        try {
            cliente.setEstado("ACT");
            //cliente.setIdCliente(new DigestUtils("MD2").digestAsHex(cliente.toString()));
            log.debug("ID Cliente generado: {}", cliente.getIdCliente());
            cliente.setFechaCreacion(new Date());
            this.clienteRepository.save(cliente);
            log.info("Se creo el cliente: {}", cliente);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el cliente.", e);
        }
    }

    @Transactional
    public void actualizar(Cliente cliente) {
        try {
            Cliente clienteAux = this.clienteRepository.findByIdCliente(cliente.getIdCliente());
            if ("ACT".equals(clienteAux.getEstado())) {
                cliente.setEstado("ACT");
                this.clienteRepository.save(cliente);
                log.info("Se actualizaron los datos del cliente: {}", cliente);
            } else {
                log.error("No se puede actualizar, Cliente: {} se encuentra INACTIVO", clienteAux);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el cliente.", e);
        }
    }

    @Transactional
    public void desactivar(String idCliente) {
        log.info("Se va a desactivar el cliente: {}", idCliente);
        try {
            Cliente cliente = this.clienteRepository.findByIdCliente(idCliente);
            log.debug("Desactivando cliente: {}, estado: INA", idCliente);
            cliente.setEstado("INA");
            this.clienteRepository.save(cliente);
            log.info("Se desactivo el cliente: {}", idCliente);
        } catch (Exception e) {
            throw new RuntimeException("Error al desactivar cliente: " + idCliente, e);
        }
    }
}

