package com.banquito.core.banking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Telefono {

    private String tipo;
    private String numero;
    private String estado;
}
