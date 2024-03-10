package com.banquito.core.clientes.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class ClienteDTO {
    
    private String idCliente;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String apellidos;
    private String nombres;
    private Date fechaNacimiento;
    private String correoElectronico;
    private String estado;
    private Date fechaCreacion;
    private Date fechaUltimoCambio;

    private List<DireccionDTO> direcciones;
    private List<TelefonoDTO> telefonos;

    public void addDireccion(DireccionDTO direccion) {
        if (this.direcciones == null) {
            this.direcciones = new ArrayList<>();
        }
        this.direcciones.add(direccion);
    }

    public void removeDireccion(DireccionDTO direccion) {
        if (this.direcciones != null) {
            this.direcciones.remove(direccion);
        }
    }

    public void addTelefono(TelefonoDTO telefono) {
        if (this.telefonos == null) {
            this.telefonos = new ArrayList<>();
        }
        this.telefonos.add(telefono);
    }

    public void removeTelefono(TelefonoDTO telefono) {
        if (this.telefonos != null) {
            this.telefonos.remove(telefono);
        }
    }
}