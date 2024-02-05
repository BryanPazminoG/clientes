package com.banquito.core.banking.clientes.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ClienteDTO other = (ClienteDTO) obj;
        if (idCliente == null) {
            if (other.idCliente != null)
                return false;
        } else if (!idCliente.equals(other.idCliente))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ClienteDTO [idCliente=" + idCliente + ", tipoIdentificacion=" + tipoIdentificacion
                + ", numeroIdentificacion=" + numeroIdentificacion + ", apellidos=" + apellidos + ", nombres=" + nombres
                + ", fechaNacimiento=" + fechaNacimiento + ", correoElectronico=" + correoElectronico + ", estado="
                + estado + ", fechaCreacion=" + fechaCreacion + ", fechaUltimoCambio=" + fechaUltimoCambio
                + ", direcciones=" + direcciones + ", telefonos=" + telefonos + "]";
    }
}
