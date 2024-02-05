package com.banquito.core.banking.clientes.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.core.banking.clientes.dao.ClienteRepository;
import com.banquito.core.banking.clientes.domain.Cliente;
import com.banquito.core.banking.clientes.dto.ClienteBuilder;
import com.banquito.core.banking.clientes.dto.ClienteDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDTO> listarTodo() {
        log.info("Se va a obtener todos los clientes naturales");
        List<ClienteDTO> dtos = new ArrayList<>();
        for (Cliente cliente : this.clienteRepository.findAll()) {
            if ("ACT".equals(cliente.getEstado())) {
                dtos.add(ClienteBuilder.toDTO(cliente));
            }
        }
        return dtos;
    }

    public ClienteDTO obtenerPorId(String id) {
        log.info("Se va a obtener el cliente con ID: {}", id);
        Cliente cliente = this.clienteRepository.findByIdCliente(id);
        if (cliente != null) {
            if ("ACT".equals(cliente.getEstado())) {
                log.debug("Cliente obtenido: {}", cliente);
                return ClienteBuilder.toDTO(cliente);
            } else {
                throw new RuntimeException("Cliente con ID: " + id + " no se encuentra activo");
            }
        } else {
            throw new RuntimeException("No existe el cliente con el ID: " + id);
        }
    }

    public ClienteDTO obtenerPorIdentificacion(String tipoIdentificacion, String numeroIdentificacion) {
        log.info("Se va a obtener cliente por TipoIdentificacion: {} y NumeroIdentificacion: {}", tipoIdentificacion,
                numeroIdentificacion);
        List<Cliente> clientes = this.clienteRepository.findByTipoIdentificacionAndNumeroIdentificacion(
                tipoIdentificacion,
                numeroIdentificacion);
        if (clientes != null && clientes.isEmpty()) {
            if ("ACT".equals(clientes.getFirst().getEstado())) {
                log.debug("Cliente obtenido: {}", clientes.getFirst());
                return ClienteBuilder.toDTO(clientes.getFirst());
            } else {
                throw new RuntimeException("Cliente con TipoIdentificacion: " + tipoIdentificacion
                    + " y NumeroIdentificacion: " + numeroIdentificacion + " no se encuentra activo");
            }
        } else {
            throw new RuntimeException("No existe el cliente con TipoIdentificacion: " + tipoIdentificacion
                    + " y NumeroIdentificacion: " + numeroIdentificacion);
        }
    }

    @Transactional
    public void crear(ClienteDTO dto) {
        try {
            Cliente cliente = ClienteBuilder.toCliente(dto);
            cliente.setEstado("ACT");
            cliente.setIdCliente(new DigestUtils("MD2").digestAsHex(cliente.toString()));
            log.debug("ID Cliente generado: {}", cliente.getIdCliente());
            cliente.setFechaCreacion(new Date());
            this.clienteRepository.save(cliente);
            log.info("Se creo el cliente: {}", cliente);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el cliente.", e);
        }
    }

    @Transactional
    public void actualizar(ClienteDTO dto) {
        try {
            Cliente clienteAux = this.clienteRepository.findByIdCliente(dto.getIdCliente());
            Cliente clienteTmp = ClienteBuilder.toCliente(dto);
            Cliente cliente = ClienteBuilder.copyCliente(clienteTmp, clienteAux);
            cliente.setEstado("ACT");
            this.clienteRepository.save(cliente);
            log.info("Se actualizaron los datos del cliente: {}", cliente);
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
