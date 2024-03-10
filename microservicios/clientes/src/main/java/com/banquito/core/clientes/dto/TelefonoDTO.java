package com.banquito.core.clientes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TelefonoDTO {
    
    private String tipo;
    private String numero;
    private String estado;
}