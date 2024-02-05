package com.banquito.core.banking.clientes.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MiembroDTO {

    private String idCliente;
    private String tipoRelacion;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private Date fechaUltimoCambio;
}
