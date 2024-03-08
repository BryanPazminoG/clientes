package com.banquito.core.empresas.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.core.empresas.dao.EmpresaRepository;
import com.banquito.core.empresas.domain.Empresa;
import com.banquito.core.empresas.domain.Miembro;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

@Slf4j
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<Empresa> listarTodo() {
        log.info("Se va a obtener todos los clientes juridicos");
        List<Empresa> dtos = new ArrayList<>();
        for (Empresa empresa : this.empresaRepository.findAll()) {
            if ("ACT".equals(empresa.getEstado())) {
                dtos.add(empresa);
            }
        }
        return dtos;
    }

    public Empresa obtenerPorId(String id) {
        log.info("Se va a obtener el cliente juridico con ID: {}", id);
        Empresa empresa = this.empresaRepository.findByIdCliente(id);
        if (empresa != null) {
            if ("ACT".equals(empresa.getEstado())) {
                log.debug("Cliente juridico obtenido: {}", empresa);
                return empresa;
            } else {
                throw new RuntimeException("Cliente juridico con ID: " + id + " no se encuentra activo");
            }
        } else {
            throw new RuntimeException("No existe el cliente juridico con el ID: " + id);
        }
    }

    public Empresa obtenerPorIdentificacion(String numeroIdentificacion) {
        log.info("Se va a obtener cliente juridico por TipoIdentificacion: RUC y NumeroIdentificacion: {}",
                numeroIdentificacion);
        List<Empresa> empresa = this.empresaRepository.findByTipoIdentificacionAndNumeroIdentificacion(
                "RUC",
                numeroIdentificacion);
        if (empresa != null && !empresa.isEmpty()) {
            if ("ACT".equals(empresa.getFirst().getEstado())) {
                log.debug("Cliente juridico obtenido: {}", empresa.getFirst());
                return empresa.getFirst();
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
    public void crear(Empresa empresa) {
        try {
            empresa.setTipoIdentificacion("RUC");
            empresa.setEstado("ACT");
            empresa.getDireccion().setEstado("ACT");
            empresa.setFechaConstitucion(new Date());
            empresa.setMiembros(new ArrayList<>());
            for (Miembro miembro : empresa.getMiembros()) {
                if (this.empresaRepository.findByIdCliente(miembro.getIdCliente()) != null) {
                    miembro.setIdCliente(miembro.getIdCliente());
                    miembro.setTipoRelacion(miembro.getTipoRelacion());
                    miembro.setFechaInicio(miembro.getFechaInicio());
                    miembro.setFechaFin(miembro.getFechaFin());
                    miembro.setEstado("ACT");
                    miembro.setFechaUltimoCambio(miembro.getFechaUltimoCambio());
                    empresa.getMiembros().add(miembro);
                } else {
                    throw new RuntimeException("Miembro con ID: " + miembro.getIdCliente() + " no existe");
                }
            }
            //empresa.setIdCliente(new DigestUtils("MD2").digestAsHex(empresa.toString()));
            log.debug("ID Cliente juridico generado: {}", empresa.getIdCliente());
            empresa.setFechaCreacion(new Date());
            this.empresaRepository.save(empresa);
            log.info("Se creo el cliente juridico : {}", empresa);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el cliente juridico", e);
        }
    }

    @Transactional
    public void actualizar(Empresa empresa) {
        try {
            Empresa empresaAux = this.empresaRepository.findByIdCliente(empresa.getIdCliente());
            if ("ACT".equals(empresaAux.getEstado())) {
                Empresa empresaTmp = empresa;
                empresaTmp.setMiembros(new ArrayList<>());
                for (Miembro miembro : empresa.getMiembros()) {                
                    if (this.empresaRepository.findByIdCliente(miembro.getIdCliente()) != null) {
                        miembro.setIdCliente(miembro.getIdCliente());
                        miembro.setTipoRelacion(miembro.getTipoRelacion());
                        miembro.setFechaInicio(miembro.getFechaInicio());
                        miembro.setFechaFin(miembro.getFechaFin());
                        miembro.setFechaUltimoCambio(new Date());
                        miembro.setEstado(miembro.getEstado());
                        empresaTmp.getMiembros().add(miembro);
                    } else {
                        log.error("Miembro con ID: " + miembro.getIdCliente() + " no existe");
                    }
                }
                empresaTmp.setFechaUltimoCambio(new Date());
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
    

