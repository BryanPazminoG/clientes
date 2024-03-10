package com.banquito.core.clientes.dto;

import java.util.ArrayList;
import java.util.List;

import com.banquito.core.clientes.domain.Cliente;
import com.banquito.core.clientes.domain.Direccion;
import com.banquito.core.clientes.domain.Telefono;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ClienteBuilder {

    public static Cliente toCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setTipoIdentificacion(dto.getTipoIdentificacion());
        cliente.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        cliente.setApellidos(dto.getApellidos());
        cliente.setNombres(dto.getNombres());
        cliente.setFechaNacimiento(dto.getFechaNacimiento());
        cliente.setCorreoElectronico(dto.getCorreoElectronico());
        cliente.setFechaUltimoCambio(dto.getFechaUltimoCambio());
        cliente.setDireccion(new ArrayList<>());
        for (DireccionDTO direcccionDTO : dto.getDirecciones()) {
            Direccion direccion = new Direccion();
            direccion.setTipo(direcccionDTO.getTipo());
            direccion.setLinea1(direcccionDTO.getLinea1());
            direccion.setLinea2(direcccionDTO.getLinea2());
            direccion.setCodigoPostal(direcccionDTO.getCodigoPostal());
            direccion.setEstado(direcccionDTO.getEstado());
            cliente.getDireccion().add(direccion);
        }
        cliente.setTelefono(new ArrayList<>());
        for (TelefonoDTO telefonoDTO : dto.getTelefonos()) {
            Telefono telefono = new Telefono();
            telefono.setTipo(telefonoDTO.getTipo());
            telefono.setNumero(telefonoDTO.getNumero());
            telefono.setEstado(telefonoDTO.getEstado());
            cliente.getTelefono().add(telefono);
        }
        return cliente;
    }

    public static ClienteDTO toDTO(Cliente cliente) {
        List<DireccionDTO> direccionesDto = new ArrayList<>();

        for (Direccion direcccion : cliente.getDireccion()) {
            DireccionDTO dto = DireccionDTO.builder().tipo(direcccion.getTipo())
                    .linea1(direcccion.getLinea1())
                    .linea2(direcccion.getLinea2())
                    .codigoPostal(direcccion.getCodigoPostal())
                    .estado(direcccion.getEstado()).build();
            direccionesDto.add(dto);
        }

        List<TelefonoDTO> telefonosDto = new ArrayList<>();

        for (Telefono telefono : cliente.getTelefono()) {
            TelefonoDTO dto = TelefonoDTO.builder().tipo(telefono.getTipo())
                    .numero(telefono.getNumero())
                    .estado(telefono.getEstado()).build();
            telefonosDto.add(dto);
        }

        ClienteDTO dto = ClienteDTO.builder().tipoIdentificacion(cliente.getTipoIdentificacion())
                .numeroIdentificacion(cliente.getNumeroIdentificacion())
                .apellidos(cliente.getApellidos())
                .nombres(cliente.getNombres())
                .fechaNacimiento(cliente.getFechaNacimiento())
                .correoElectronico(cliente.getCorreoElectronico())
                .estado(cliente.getEstado())
                .fechaCreacion(cliente.getFechaCreacion())
                .fechaUltimoCambio(cliente.getFechaUltimoCambio())
                .direcciones(direccionesDto)
                .telefonos(telefonosDto)
                .idCliente(cliente.getIdCliente()).build();
        return dto;
    }

    public static Cliente copyCliente(Cliente source, Cliente destiny) {
        if (source.getId() != null) {
            destiny.setId(source.getId());
        }

        if (source.getIdCliente() != null) {
            destiny.setIdCliente(source.getIdCliente());
        }

        if (source.getTipoIdentificacion() != null) {
            destiny.setTipoIdentificacion(source.getTipoIdentificacion());
        }

        if (source.getNumeroIdentificacion() != null) {
            destiny.setNumeroIdentificacion(source.getNumeroIdentificacion());
        }

        if (source.getApellidos() != null) {
            destiny.setApellidos(source.getApellidos());
        }

        if (source.getNombres() != null) {
            destiny.setNombres(source.getNombres());
        }

        destiny.setFechaNacimiento(source.getFechaNacimiento());

        if (source.getCorreoElectronico() != null) {
            destiny.setCorreoElectronico(source.getCorreoElectronico());
        }

        if (source.getCorreoElectronico() != null) {
            destiny.setCorreoElectronico(source.getCorreoElectronico());
        }

        destiny.setEstado(source.getEstado());
        destiny.setFechaCreacion(source.getFechaCreacion());
        destiny.setFechaUltimoCambio(source.getFechaUltimoCambio());
        destiny.setDireccion(source.getDireccion());
        destiny.setTelefono(source.getTelefono());
        return destiny;
    }
}