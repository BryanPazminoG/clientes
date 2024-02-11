package com.banquito.core.banking.clientes.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.core.banking.clientes.dao.ClienteRepository;
import com.banquito.core.banking.clientes.dao.EmpresaRepository;
import com.banquito.core.banking.clientes.domain.Empresa;
import com.banquito.core.banking.clientes.domain.Miembro;
import com.banquito.core.banking.clientes.dto.EmpresaBuilder;
import com.banquito.core.banking.clientes.dto.EmpresaDTO;
import com.banquito.core.banking.clientes.dto.MiembroDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final ClienteRepository clienteRepository;

    public EmpresaService(EmpresaRepository empresaRepository, ClienteRepository clienteRepository) {
        this.empresaRepository = empresaRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<EmpresaDTO> listarTodo() {
        log.info("Se va a obtener todos los clientes juridicos");
        List<EmpresaDTO> dtos = new ArrayList<>();
        for (Empresa empresa : this.empresaRepository.findAll()) {
            if ("ACT".equals(empresa.getEstado())) {
                dtos.add(EmpresaBuilder.toDTO(empresa));
            }
        }
        return dtos;
    }

    public EmpresaDTO obtenerPorId(String id) {
        log.info("Se va a obtener el cliente juridico con ID: {}", id);
        Empresa empresa = this.empresaRepository.findByIdCliente(id);
        if (empresa != null) {
            if ("ACT".equals(empresa.getEstado())) {
                log.debug("Cliente juridico obtenido: {}", empresa);
                return EmpresaBuilder.toDTO(empresa);
            } else {
                throw new RuntimeException("Cliente juridico con ID: " + id + " no se encuentra activo");
            }
        } else {
            throw new RuntimeException("No existe el cliente juridico con el ID: " + id);
        }
    }

    public EmpresaDTO obtenerPorIdentificacion(String numeroIdentificacion) {
        log.info("Se va a obtener cliente juridico por TipoIdentificacion: RUC y NumeroIdentificacion: {}",
                numeroIdentificacion);
        List<Empresa> empresas = this.empresaRepository.findByTipoIdentificacionAndNumeroIdentificacion(
                "RUC",
                numeroIdentificacion);
        if (empresas != null && !empresas.isEmpty()) {
            if ("ACT".equals(empresas.getFirst().getEstado())) {
                log.debug("Cliente juridico obtenido: {}", empresas.getFirst());
                return EmpresaBuilder.toDTO(empresas.getFirst());
            } else {
                throw new RuntimeException("Cliente juridico con TipoIdentificacion: RUC y NumeroIdentificacion: "
                        + numeroIdentificacion + " no se encuentra activo");
            }
        } else {
            throw new RuntimeException(
                    "No existe el cliente juridico con TipoIdentificacion: RUC y NumeroIdentificacion: "
                            + numeroIdentificacion);
        }
    }

    @Transactional
    public void crear(EmpresaDTO dto) {
        try {
            Empresa empresa = EmpresaBuilder.toEmpresa(dto);
            empresa.setTipoIdentificacion("RUC");
            empresa.setEstado("ACT");
            empresa.getDireccion().setEstado("ACT");
            empresa.setFechaCreacion(new Date());
            empresa.setMiembros(new ArrayList<>());
            for (MiembroDTO miembroDTO : dto.getMiembros()) {
                if (this.clienteRepository.findByIdCliente(miembroDTO.getIdCliente()) != null) {
                    Miembro miembro = new Miembro();
                    miembro.setIdCliente(miembroDTO.getIdCliente());
                    miembro.setTipoRelacion(miembroDTO.getTipoRelacion());
                    miembro.setFechaInicio(miembroDTO.getFechaInicio());
                    miembro.setFechaFin(miembroDTO.getFechaFin());
                    miembro.setEstado("ACT");
                    miembro.setFechaUltimoCambio(miembroDTO.getFechaUltimoCambio());
                    empresa.getMiembros().add(miembro);
                } else {
                    throw new RuntimeException("Miembro con ID: " + miembroDTO.getIdCliente() + " no existe");
                }
            }

            empresa.setIdCliente(new DigestUtils("MD2").digestAsHex(empresa.toString()));
            log.debug("ID Cliente juridico generado: {}", empresa.getIdCliente());
            empresa.setFechaCreacion(new Date());
            this.empresaRepository.save(empresa);
            log.info("Se creo el cliente juridico : {}", empresa);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el cliente juridico", e);
        }
    }

    @Transactional
    public void actualizar(EmpresaDTO dto) {
        try {
            Empresa empresaAux = this.empresaRepository.findByIdCliente(dto.getIdCliente());
            if ("ACT".equals(empresaAux.getEstado())) {
                Empresa empresaTmp = EmpresaBuilder.toEmpresa(dto);
                empresaTmp.setMiembros(new ArrayList<>());
                for (MiembroDTO miembroDTO : dto.getMiembros()) {                
                    if (this.clienteRepository.findByIdCliente(miembroDTO.getIdCliente()) != null) {
                        Miembro miembro = new Miembro();
                        miembro.setIdCliente(miembroDTO.getIdCliente());
                        miembro.setTipoRelacion(miembroDTO.getTipoRelacion());
                        miembro.setFechaInicio(miembroDTO.getFechaInicio());
                        miembro.setFechaFin(miembroDTO.getFechaFin());
                        miembro.setFechaUltimoCambio(new Date());
                        miembro.setEstado(miembroDTO.getEstado());
                        empresaTmp.getMiembros().add(miembro);
                    } else {
                        log.error("Miembro con ID: " + miembroDTO.getIdCliente() + " no existe");
                    }
                }
                empresaTmp.setFechaUltimoCambio(new Date());
                Empresa empresa = EmpresaBuilder.copyEmpresa(empresaTmp, empresaAux);
                empresa.setEstado("ACT");
                this.empresaRepository.save(empresa);
                log.info("Se actualizaron los datos del cliente juridico: {}", empresa);
            } else {
                log.error("No se puede actualizar, Cliente juridico: {} se encuentra INACTIVO", empresaAux);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el cliente juridico", e);
        }
    }

    @Transactional
    public void quitarMiembroEmpresa(String idEmpresa, String idMiembro) {
        try {
            Empresa empresa = this.empresaRepository.findByIdCliente(idEmpresa);
            for (Miembro miembro : empresa.getMiembros()) {
                if (idMiembro.equals(miembro.getIdCliente())) {
                    miembro.setEstado("INA");
                    log.info("Se desactivo miembro: {} de cliente juridico: {}", idMiembro, idEmpresa);
                    break;
                }
            }
            log.debug("Desactivando miembro: {} de cliente juridico: {}", idMiembro, idEmpresa);
            this.empresaRepository.save(empresa);
        } catch (Exception e) {
            throw new RuntimeException("Error al desactivar miembro: " + idMiembro, e);
        }
    }

    @Transactional
    public void desactivar(String idCliente) {
        try {
            Empresa empresa = this.empresaRepository.findByIdCliente(idCliente);
            for (Miembro miembro : empresa.getMiembros()) {
                if ("ACT".equals(miembro.getEstado())) {
                    log.error("Cliente juridico: {}, tiene miembros activos", idCliente);
                    throw new RuntimeException("Miembro con ID: " + miembro.getIdCliente() + " se encuentra activo");
                }
            }
            log.debug("Desactivando cliente juridico: {}, estado: INA", idCliente);
            empresa.setEstado("INA");
            this.empresaRepository.save(empresa);
            log.info("Se desactivo el cliente juridico: {}", idCliente);
        } catch (Exception e) {
            throw new RuntimeException("Error al desactivar cliente juridico: " + idCliente, e);
        }
    }
}
