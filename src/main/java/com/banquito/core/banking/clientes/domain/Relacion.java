package com.banquito.core.banking.clientes.domain;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Relacion {

    @Field("cliente_natural_id")
    private String clienteId;

    @Field("tipo_relacion")
    private String tipoRelacion;

    @Field("fecha_inicio")
    private Date fechaInicio;

    @Field("fecha_fin")
    private Date fechaFin;

    private String estado;

    @Field("fecha_ultimo_cambio")
    private LocalDateTime fechaUltimoCambio;
}

