package com.banquito.core.clientes.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "clientes")
@CompoundIndexes({
        @CompoundIndex(name = "idxu_cliente_identificacion", def = "{'tipo_identificacion': 1, 'numero_identificacion': 1}", unique = true)
})
public class Cliente {

    @Id
    private String id;

    @Indexed(unique = true)
    @Field("id_cliente")
    private String idCliente;

    @Field("tipo_identificacion")
    private String tipoIdentificacion;

    @Field("numero_identificacion")
    private String numeroIdentificacion;

    private String apellidos;
    private String nombres;

    @Field("fecha_nacimiento")
    private Date fechaNacimiento;

    private List<Direccion> direcciones;

    @Field("correo_electronico")
    private String correoElectronico;

    private List<Telefono> telefonos;
    private String estado;

    @Field("fecha_creacion")
    private Date fechaCreacion;

    @Field("fecha_ultimo_cambio")
    private Date fechaUltimoCambio;

    @Version
    private Long version;

    public Cliente (String id) {
        this.id = id;
    }


}
