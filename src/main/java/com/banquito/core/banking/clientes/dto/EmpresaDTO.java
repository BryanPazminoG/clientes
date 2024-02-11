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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmpresaDTO other = (EmpresaDTO) obj;
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
        return "EmpresaDTO [idCliente=" + idCliente + ", tipoIdentificacion=" + tipoIdentificacion
                + ", numeroIdentificacion=" + numeroIdentificacion + ", fechaConstitucion=" + fechaConstitucion
                + ", razonSocial=" + razonSocial + ", nombreComercial=" + nombreComercial + ", direccion=" + direccion
                + ", correoElectronico=" + correoElectronico + ", telefono=" + telefono + ", estado=" + estado
                + ", fechaCreacion=" + fechaCreacion + ", fechaUltimoCambio=" + fechaUltimoCambio + ", miembros="
                + miembros + "]";
    }
}
