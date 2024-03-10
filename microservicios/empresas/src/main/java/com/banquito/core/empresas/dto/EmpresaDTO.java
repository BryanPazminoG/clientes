package com.banquito.core.empresas.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmpresaDTO {
    
    private String idCliente;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private Date fechaConstitucion;
    private String razonSocial;
    private String nombreComercial;
    private DireccionDTO direccion;
    private String correoElectronico;
    private String telefono;
    private String estado;
    private Date fechaCreacion;
    private Date fechaUltimoCambio;

    private List<MiembroDTO> miembros;

    public void addMiembro(MiembroDTO miembro) {
        if (this.miembros == null) {
            this.miembros = new ArrayList<>();
        }
        this.miembros.add(miembro);
    }

    public void removeDireccion(MiembroDTO miembro) {
        if (this.miembros != null) {
            this.miembros.remove(miembro);
        }
    }
}