package com.banquito.core.banking.clientes.domain;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="CLIENTE_PERSONA_RELACION")
public class ClientePersonaRelacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_CLIENTE_PERSONA_RELACION", nullable = false)
    private Integer codigo;

    @Column(name = "COD_TIPO_RELACION", nullable = false, length = 3)
    private String codigoTipoRelacion;

    @Column(name = "COD_CLIENTE_EMPRESA", nullable = false)
    private Integer codigoClienteEmpresa;

    @Column(name = "COD_CLIENTE_PERSONA", nullable = false)
    private Integer codigoClientePersona;

    @Column(name = "ESTADO", nullable = false, length = 3)
    private String estado;

    @Column(name = "FECHA_INICIO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "FECHA_FIN", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @ManyToOne
    @JoinColumn(name = "COD_TIPO_RELACION", referencedColumnName = "COD_TIPO_RELACION", insertable = false, updatable = false)
    private TipoRelacion tipoRelacion;

    //@ManyToOne
    //@JoinColumn(name = "COD_CLIENTE_EMPRESA", referencedColumnName = "COD_CLIENTE", insertable = false, updatable = false)
    //private Cliente clienteEmpresa;

    //@ManyToOne
    //@JoinColumn(name = "COD_CLIENTE_PERSONA", referencedColumnName = "COD_CLIENTE", insertable = false, updatable = false)
    //private Cliente clientePersona;

    @Version
    private Long version;

    public ClientePersonaRelacion(){}
    
    public ClientePersonaRelacion(Integer codigo){
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
        ClientePersonaRelacion other = (ClientePersonaRelacion) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ClientePersonaRelacion [codigo=" + codigo + ", codigoTipoRelacion=" + codigoTipoRelacion
                + ", codigoClienteEmpresa=" + codigoClienteEmpresa + ", codigoClientePersona=" + codigoClientePersona
                + ", estado=" + estado + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", tipoRelacion="
                + tipoRelacion + ", version=" + version + "]";
    }



}
