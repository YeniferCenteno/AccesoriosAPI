package org.esfe.dtos.tipodeaccesorio;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TipoDeAccesorioModificar implements Serializable {
    private Integer id;
    private String nombre;
}
