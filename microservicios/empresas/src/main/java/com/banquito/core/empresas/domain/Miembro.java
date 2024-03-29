package com.banquito.core.empresas.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Miembro {

    @Field("cod_cliente")
    private String codCliente;

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