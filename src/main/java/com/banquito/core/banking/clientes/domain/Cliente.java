package com.banquito.core.banking.clientes.domain;

import java.sql.Date;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CLIENTE", nullable = false)
    private Integer codigo;

    @Column(name = "TIPO_CLIENTE", nullable = false, length = 3)
    private String tipoCliente;

    @Column(name = "TIPO_IDENTIFICACION", nullable = false, length = 3)
    private String tipoIdentificacion;

    @Column(name = "NUMERO_IDENTIFICACION", nullable = false, length = 20)
    private String numeroIdentificacion;

    @Column(name = "APELLIDOS", nullable = false, length = 100)
    private String apellidos;

    @Column(name = "NOMBRES", nullable = false, length = 100)
    private String nombres;

    @Column(name = "FECHA_NACIMIENTO", nullable = false)    
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "FECHA_CONSTITUCION")
    @Temporal(TemporalType.DATE)
    private Date fechaConstitucion;

    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;

    @Column(name = "NOMBRE_COMERCIAL")
    private String nombreComercial;

    @Column(name = "DIRECCION", nullable = false, length = 200)
    private String direccion;

    @Column(name = "CORREO_ELECTRONICO", nullable = false, length = 100)
    private String correoElectronico;

    @Column(name = "TELEFONO", nullable = false, length = 15)
    private String telefono;

    @Column(name = "FECHA_ULTIMO_CAMBIO", nullable = false, length = 15)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaModificacion;

    @Version
    private Long version;

    public Cliente(){}

    public Cliente(Integer codigo){
        this.codigo = codigo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cliente [codigo=" + codigo + ", tipoCliente=" + tipoCliente + ", tipoIdentificacion="
                + tipoIdentificacion + ", numeroIdentificacion=" + numeroIdentificacion + ", apellidos=" + apellidos
                + ", nombres=" + nombres + ", fechaNacimiento=" + fechaNacimiento + ", fechaConstitucion="
                + fechaConstitucion + ", razonSocial=" + razonSocial + ", nombreComercial=" + nombreComercial
                + ", direccion=" + direccion + ", correoElectronico=" + correoElectronico + ", telefono=" + telefono
                + ", version=" + version + "]";
    }


}
