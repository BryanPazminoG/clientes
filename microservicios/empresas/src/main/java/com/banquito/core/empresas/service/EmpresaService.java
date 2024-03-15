package com.banquito.core.empresas.service;

import lombok.extern.slf4j.Slf4j;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.core.empresas.dao.EmpresaRepository;
import com.banquito.core.empresas.domain.Empresa;
import com.banquito.core.empresas.domain.Miembro;
import com.banquito.core.empresas.service.ExternalServices.ClienteNaturalRestService;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

@Slf4j
@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final ClienteNaturalRestService clienteNaturalRestService;

    public EmpresaService(EmpresaRepository empresaRepository, ClienteNaturalRestService clienteNaturalRestService) {
        this.empresaRepository = empresaRepository;
        this.clienteNaturalRestService = clienteNaturalRestService;
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
        Empresa empresa = this.empresaRepository.findByCodEmpresa(id);
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
            if ("ACT".equals(empresa.get(0).getEstado())) {
                log.debug("Cliente juridico obtenido: {}", empresa.get(0));
                return empresa.get(0);
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
            empresa.setFechaCreacion(new Date());
            ArrayList<Miembro> nuevaListaMiembros = new ArrayList<>();
            for (Miembro miembroJSON : empresa.getMiembros()) {
                Miembro nuevoMiembro = new Miembro();
                if (clienteNaturalRestService.obtenerPorId(miembroJSON.getCodCliente()) != null ){
                    nuevoMiembro.setCodCliente(miembroJSON.getCodCliente());
                    nuevoMiembro.setTipoRelacion(miembroJSON.getTipoRelacion());
                    nuevoMiembro.setFechaInicio(miembroJSON.getFechaInicio());
                    nuevoMiembro.setFechaFin(miembroJSON.getFechaFin());
                    nuevoMiembro.setEstado("ACT");
                    nuevoMiembro.setFechaUltimoCambio(new Date());
                    nuevaListaMiembros.add(nuevoMiembro);
                } else {
                     throw new RuntimeException("Cliente natural no encontrado.");
                }
            }
            empresa.setMiembros(nuevaListaMiembros);
            empresa.setCodEmpresa(new DigestUtils("MD2").digestAsHex(empresa.toString()));
            log.debug("ID Cliente juridico generado: {}", empresa.getCodEmpresa());
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
            Empresa empresaAux = this.empresaRepository.findByCodEmpresa(empresa.getCodEmpresa());

            if (empresaAux != null && "ACT".equals(empresaAux.getEstado())) {
                empresaAux.setNombreComercial(empresa.getNombreComercial());
                empresaAux.setRazonSocial(empresa.getRazonSocial());
                empresaAux.setTipoIdentificacion(empresa.getTipoIdentificacion());
                empresaAux.setFechaCreacion(empresa.getFechaCreacion());
                empresaAux.setDireccion(empresa.getDireccion());
                
                ArrayList<Miembro> nuevaListaMiembros = new ArrayList<>();
                for (Miembro miembroJSON : empresa.getMiembros()) {
                    Miembro nuevoMiembro = new Miembro();
                    if (clienteNaturalRestService.obtenerPorId(miembroJSON.getCodCliente()) != null ){
                        nuevoMiembro.setCodCliente(miembroJSON.getCodCliente());
                        nuevoMiembro.setTipoRelacion(miembroJSON.getTipoRelacion());
                        nuevoMiembro.setFechaInicio(miembroJSON.getFechaInicio());
                        nuevoMiembro.setFechaFin(miembroJSON.getFechaFin());
                        nuevoMiembro.setEstado(miembroJSON.getEstado());
                        nuevoMiembro.setFechaUltimoCambio(new Date());
                        nuevaListaMiembros.add(nuevoMiembro);
                    } else {
                        throw new RuntimeException("Cliente natural no encontrado.");
                    }
                }
                empresaAux.setMiembros(nuevaListaMiembros);
                empresaAux.setFechaUltimoCambio(new Date());
                this.empresaRepository.save(empresaAux);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el cliente jurídico", e);
        }
    }
    
    public void quitarMiembroEmpresa(String idEmpresa, String idMiembro) {
        try {
            Empresa empresa = this.empresaRepository.findByCodEmpresa(idEmpresa);
            if (empresa != null){
                for (Miembro miembro : empresa.getMiembros()) {
                    if (idMiembro.equals(miembro.getCodCliente())) {
                        miembro.setEstado("INA");
                        log.info("Se desactivo miembro: {} de cliente juridico: {}", idMiembro, idEmpresa);
                        break;
                    }
                }
                log.debug("Desactivando miembro: {} de cliente juridico: {}", idMiembro, idEmpresa);
                this.empresaRepository.save(empresa);
            } else {
                log.error("No exist el cliente juridico", idMiembro);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al desactivar miembro: " + idMiembro, e);
        }
    }

    public void desactivar(String idCliente) {
        try {
            Empresa empresa = this.empresaRepository.findByCodEmpresa(idCliente);
            if (empresa != null){
                for (Miembro miembro : empresa.getMiembros()) {
                    if ("ACT".equals(miembro.getEstado())) {
                        log.error("Cliente juridico: {}, tiene miembros activos", idCliente);
                        throw new RuntimeException("Miembro con ID: " + miembro.getCodCliente() + " se encuentra activo");
                    }
                }
                log.debug("Desactivando cliente juridico: {}, estado: INA", idCliente);
                empresa.setEstado("INA");
                this.empresaRepository.save(empresa);
                log.info("Se desactivo el cliente juridico: {}", idCliente);
            } else {
                log.error("No exist el cliente juridico", idCliente);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al desactivar cliente juridico: " + idCliente, e);
        }
    }
}
    

