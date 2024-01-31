package com.banquito.core.banking.clientes.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.clientes.dao.ClienteRepository;
import com.banquito.core.banking.clientes.domain.Cliente;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodo(){
        return clienteRepository.findAll(); 
    }

    public Cliente obtenerPorId(String id) {
        log.info("Se va a obtener cliente con ID: {}", id);
        Optional<Cliente> optionalCliente = this.clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
            return optionalCliente.get();
        } else {
            throw new RuntimeException("NO existe el cliente con el id: " + id);
        }
    }

    public Cliente obtenerPorIdentificacion(String tipoIdentificacion, String numeroIdentificacion) {
        log.info("Se va a obtener cliente por TipoIdentificacion: {} y NumeroIdentificacion: {}", tipoIdentificacion, numeroIdentificacion);
        List<Cliente> clientes = this.clienteRepository.findByTipoIdentificacionAndNumeroIdentificacion(tipoIdentificacion,
        numeroIdentificacion);
        if (clientes != null && clientes.isEmpty()) {
            log.debug("Cliente obtenido: {}", clientes.getFirst());
            return clientes.getFirst();
        } else {
            throw new RuntimeException("No existe el cliente con tipoIdentificacion: "+ tipoIdentificacion + " y numeroIdentificacion: " + numeroIdentificacion);
        }
    }

    public void crear(Cliente cliente) {
        try {
            cliente.setFechaCreacion(LocalDateTime.now());
            this.clienteRepository.save(cliente);
            log.info("Se creo el cliente: {}", cliente);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el cliente.", e);
        }
    }

    public void actualizar(Cliente cliente) {
        try {
            this.clienteRepository.save(cliente);
            log.info("Se actualizaron los datos del cliente: {}", cliente);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el cliente.", e);
        }
    }
}
