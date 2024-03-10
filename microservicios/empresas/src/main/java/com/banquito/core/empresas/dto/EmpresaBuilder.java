package com.banquito.core.empresas.dto;

import java.util.ArrayList;
import java.util.List;

import com.banquito.core.empresas.domain.Direccion;
import com.banquito.core.empresas.domain.Empresa;
import com.banquito.core.empresas.domain.Miembro;

public class EmpresaBuilder {

    public static Empresa toEmpresa(EmpresaDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setTipoIdentificacion(dto.getTipoIdentificacion());
        empresa.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        empresa.setFechaConstitucion(dto.getFechaConstitucion());
        empresa.setRazonSocial(dto.getRazonSocial());
        empresa.setNombreComercial(dto.getNombreComercial());
        empresa.setCorreoElectronico(dto.getCorreoElectronico());
        empresa.setTelefono(dto.getTelefono());
        empresa.setFechaUltimoCambio(dto.getFechaUltimoCambio());

        Direccion direccion = new Direccion();
        direccion.setTipo(dto.getDireccion().getTipo());
        direccion.setLinea1(dto.getDireccion().getLinea1());
        direccion.setLinea2(dto.getDireccion().getLinea2());
        direccion.setCodigoPostal(dto.getDireccion().getCodigoPostal());
        empresa.setDireccion(direccion);

        return empresa;
    }

    public static EmpresaDTO toDTO(Empresa empresa) {
        List<MiembroDTO> miembrosDto = new ArrayList<>();

        for (Miembro miembro : empresa.getMiembros()) {
            MiembroDTO dto = MiembroDTO.builder().idCliente(miembro.getIdCliente())
                    .tipoRelacion(miembro.getTipoRelacion())
                    .fechaInicio(miembro.getFechaInicio())
                    .fechaFin(miembro.getFechaFin())
                    .fechaUltimoCambio(miembro.getFechaUltimoCambio())
                    .estado(miembro.getEstado()).build();
            miembrosDto.add(dto);
        }

        DireccionDTO direccionDto = DireccionDTO.builder().tipo(empresa.getDireccion().getTipo())
                .linea1(empresa.getDireccion().getLinea1())
                .linea2(empresa.getDireccion().getLinea2())
                .codigoPostal(empresa.getDireccion().getCodigoPostal())
                .estado(empresa.getDireccion().getEstado()).build();

        EmpresaDTO dto = EmpresaDTO.builder().tipoIdentificacion(empresa.getTipoIdentificacion())
                .numeroIdentificacion(empresa.getNumeroIdentificacion())
                .fechaConstitucion(empresa.getFechaConstitucion())
                .razonSocial(empresa.getRazonSocial())
                .nombreComercial(empresa.getNombreComercial())
                .direccion(direccionDto)
                .correoElectronico(empresa.getCorreoElectronico())
                .telefono(empresa.getTelefono())
                .estado(empresa.getEstado())
                .fechaCreacion(empresa.getFechaCreacion())
                .fechaUltimoCambio(empresa.getFechaUltimoCambio())
                .miembros(miembrosDto)
                .idCliente(empresa.getIdCliente()).build();
        return dto;
    }

    public static Empresa copyEmpresa(Empresa source, Empresa destiny) {
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

        if (source.getFechaConstitucion() != null) {
            destiny.setFechaConstitucion(source.getFechaConstitucion());
        }

        if (source.getRazonSocial() != null) {
            destiny.setRazonSocial(source.getRazonSocial());
        }

        destiny.setNombreComercial(source.getNombreComercial());
        destiny.setDireccion(source.getDireccion());
        destiny.setTelefono(source.getTelefono());

        if (source.getCorreoElectronico() != null) {
            destiny.setCorreoElectronico(source.getCorreoElectronico());
        }

        destiny.setEstado(source.getEstado());
        destiny.setFechaCreacion(source.getFechaCreacion());
        destiny.setFechaUltimoCambio(source.getFechaUltimoCambio());
        destiny.setMiembros(source.getMiembros());
        return destiny;
    }
}