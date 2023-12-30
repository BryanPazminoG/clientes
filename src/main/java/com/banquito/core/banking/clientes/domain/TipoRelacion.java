package com.banquito.core.banking.clientes.domain;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="TIPO_RELACION")
public class TipoRelacion {

    @Id
    @Column(name = "COD_TIPO_RELACION", nullable = false, length = 3)
    private String codigo;

    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;
    
    @Column(name = "FECHA_ULTIMO_CAMBIO", nullable = false, length = 15)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaModificacion;
    
    @Version
    private Long version;
    
    public TipoRelacion(){}

    public TipoRelacion(String codigo){
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
        TipoRelacion other = (TipoRelacion) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TipoRelacion [codigo=" + codigo + ", nombre=" + nombre + ", version=" + version + "]";
    }

    
    

}
