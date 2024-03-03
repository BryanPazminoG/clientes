package com.banquito.core.banking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
public class Direccion {

    private String tipo;
    private String linea1;
    private String linea2;
    @Field("codigo_postal")
    private String codigoPostal;
    private String estado;
}