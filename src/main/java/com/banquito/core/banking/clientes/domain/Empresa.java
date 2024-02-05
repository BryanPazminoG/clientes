package com.banquito.core.banking.clientes.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "empresas")
@CompoundIndexes({
        @CompoundIndex(name = "idxu_cliente_identificacion", def = "{'tipo_identificacion': 1, 'numero_identificacion': 1}", unique = true)
})
public class Empresa {
    @Id
    private String id;

    @Indexed(unique = true)
    @Field("id_cliente")
    private String idCliente;

    @Field("tipo_identificacion")
    private String tipoIdentificacion;

    @Field("numero_identificacion")
    private String numeroIdentificacion;

    @Field("fecha_constitucion")
    private Date fechaConstitucion;

    @Field("razon_social")
    private String razonSocial;

    @Field("nombre_comercial")
    private String nombreComercial;

    private Direccion direccion;

    @Field("correo_electronico")
    private String correoElectronico;

    private String telefono;
    private String estado;

    @Field("fecha_creacion")
    private Date fechaCreacion;

    @Field("fecha_ultimo_cambio")
    private Date fechaUltimoCambio;

    private List<Miembro> miembros;

    @Version
    private Long version;

    public Empresa (String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Empresa other = (Empresa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Empresa [id=" + id + ", idCliente=" + idCliente + ", tipoIdentificacion=" + tipoIdentificacion
                + ", numeroIdentificacion=" + numeroIdentificacion + ", fechaConstitucion=" + fechaConstitucion
                + ", razonSocial=" + razonSocial + ", nombreComercial=" + nombreComercial + ", direccion=" + direccion
                + ", correoElectronico=" + correoElectronico + ", telefono=" + telefono + ", estado=" + estado
                + ", fechaCreacion=" + fechaCreacion + ", fechaUltimoCambio=" + fechaUltimoCambio + ", miembros="
                + miembros + ", version=" + version + "]";
    }
}
