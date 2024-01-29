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
    
    private String calle;
    private String numero;

    @Field("codigo_postal")
    private String codigoPostal;
    
    private String ciudad;
    private String provincia;
    private String pais;
    private String referencia;
}
