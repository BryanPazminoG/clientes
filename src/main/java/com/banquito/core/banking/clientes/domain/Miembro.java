package com.banquito.core.banking.clientes.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Miembro {

    @Field("id_cliente")
    private String idCliente;

    @Field("tipo_relacion")
    private String tipoRelacion;

    @Field("fecha_inicio")
    private Date fechaInicio;

    @Field("fecha_fin")
    private Date fechaFin;

    private String estado;

    @Field("fecha_ultimo_cambio")
    private Date fechaUltimoCambio;
}
