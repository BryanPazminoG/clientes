package com.banquito.core.banking.clientes.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Clientes")
@CompoundIndexes({
        @CompoundIndex(name = "idxu_cliente_identificacion", def = "{'tipo_identificacion': 1, 'numero_identificacion': 1}", unique = true)
})
public class ClienteNatural {

    @Id
    private String id;

    @Field("tipo_identificacion")
    private String tipoIdentificacion;
    
    @Field("numero_identificacion")
    private String numeroIdentificacion;

    private String apellidos;
    private String nombres;

    @Field("fecha_nacimiento")
    private Date fechaNacimiento;

    private List<Direccion> direccion;

    @Field("correo_electronico")
    private String correoElectronico;

    private List<Telefono> telefono;

    @Field("fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Field("fecha_ultimo_cambio")
    private LocalDateTime fechaUltimoCambio;

    private String estado;

    @Version
    private Long version;

    public ClienteNatural(String id) {
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
        ClienteNatural other = (ClienteNatural) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
