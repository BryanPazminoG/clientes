package com.banquito.core.clientes.dto;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DireccionDTO {

    private String tipo;    
    private String linea1;
    private String linea2;
    @Field("codigo_postal")
    private String codigoPostal;
    private String estado;
}