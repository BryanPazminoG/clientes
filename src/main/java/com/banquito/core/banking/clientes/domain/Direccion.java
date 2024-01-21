package com.banquito.core.banking.clientes.domain;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Direccion {

    @Field("tipo_direccion")
    private String tipoDireccion;
    
    private String ubicacion;
    private String referencia;
}
