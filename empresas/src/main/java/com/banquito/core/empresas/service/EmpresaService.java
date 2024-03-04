package com.banquito.core.empresas.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.banquito.core.empresas.dao.EmpresaRepository;
import com.banquito.core.empresas.domain.Empresa;

import java.util.List;
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
    
}
