package com.banquito.core.banking.clientes.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name="TIPO_RELACION")
public class TipoRelacion {

    @Id
    @Column(name = "COD_TIPO_RELACION", nullable = false, length = 3)
    private String codigo;

    @Column(name = "NOMBRE", nullable = false, length = 30)
    private String nombre;

    //@OneToMany(mappedBy = "tipoRelacion")
    //private List<ClientePersonaRelacion> clientePersonaRelacion;

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

}
