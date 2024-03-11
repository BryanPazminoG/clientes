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
        Empresa empresa = this.empresaRepository.findByIdEmpresa(id);
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
    
            // Crear una lista nueva para los miembros de la empresa
            ArrayList<Miembro> nuevaListaMiembros = new ArrayList<>();
    
            // Iterar sobre los miembros del JSON y agregarlos a la nueva lista
            for (Miembro miembroJSON : empresa.getMiembros()) {
                Miembro nuevoMiembro = new Miembro();
                nuevoMiembro.setIdCliente(miembroJSON.getIdCliente());
                nuevoMiembro.setTipoRelacion(miembroJSON.getTipoRelacion());
                nuevoMiembro.setFechaInicio(miembroJSON.getFechaInicio());
                nuevoMiembro.setFechaFin(miembroJSON.getFechaFin());
                nuevoMiembro.setEstado("ACT");
                nuevoMiembro.setFechaUltimoCambio(new Date());
                nuevaListaMiembros.add(nuevoMiembro);
            }
    
            // Actualizar la lista de miembros de la empresa con la nueva lista
            empresa.setMiembros(nuevaListaMiembros);

            //empresa.setMiembros(new ArrayList<>());

            


            // for (Miembro miembro : empresa.getMiembros()) {
            //     miembro.setIdCliente(miembro.getIdCliente());
            //     miembro.setTipoRelacion(miembro.getTipoRelacion());
            //     miembro.setFechaInicio(miembro.getFechaInicio());
            //     miembro.setFechaFin(miembro.getFechaFin());
            //     miembro.setEstado("ACT");
            //     miembro.setFechaUltimoCambio(miembro.getFechaUltimoCambio());
            //     empresa.getMiembros().add(miembro);
            // }

            empresa.setIdEmpresa(new DigestUtils("MD2").digestAsHex(empresa.toString()));
            log.debug("ID Cliente juridico generado: {}", empresa.getIdEmpresa());
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
            Empresa empresaAux = this.empresaRepository.findByIdEmpresa(empresa.getIdEmpresa());
            if ("ACT".equals(empresaAux.getEstado())) {
                Empresa empresaTmp = empresa;
                empresaTmp.setMiembros(new ArrayList<>());
                for (Miembro miembro : empresa.getMiembros()) {                
                    if (clienteNaturalRestService.obtenerPorId(miembro.getIdCliente()) != null) {
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

    public void quitarMiembroEmpresa(String idEmpresa, String idMiembro) {
        try {
            Empresa empresa = this.empresaRepository.findByIdEmpresa(idEmpresa);
            if (empresa != null){
                for (Miembro miembro : empresa.getMiembros()) {
                    if (idMiembro.equals(miembro.getIdCliente())) {
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
            Empresa empresa = this.empresaRepository.findByIdEmpresa(idCliente);
            if (empresa != null){
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
            } else {
                log.error("No exist el cliente juridico", idCliente);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error al desactivar cliente juridico: " + idCliente, e);
        }
    }
}
    

