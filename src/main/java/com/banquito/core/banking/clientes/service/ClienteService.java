package com.banquito.core.banking.clientes.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.banking.clientes.dao.ClienteRepository;
import com.banquito.core.banking.clientes.dao.TipoRelacionRepository;
import com.banquito.core.banking.clientes.domain.Cliente;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final TipoRelacionRepository tipoRelacionRepository;

    public ClienteService(ClienteRepository clienteRepository, TipoRelacionRepository tipoRelacionRepository) {
        this.clienteRepository = clienteRepository;
        this.tipoRelacionRepository = tipoRelacionRepository;
    }

    public Cliente obtainById(Integer id){
        try {
            Optional<Cliente> opt = this.clienteRepository.findById(id);
            if(opt.isPresent()){
                return opt.get();
            }   else {
                
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    } 


}
