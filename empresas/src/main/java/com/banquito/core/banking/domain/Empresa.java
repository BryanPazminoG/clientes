package com.banquito.core.banking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

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

    public Empresa(String id) {
        this.id = id;
    }

}