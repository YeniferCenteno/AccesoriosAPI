package org.esfe.dtos.usuario;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UsuarioSalida {
    private Integer id;

    private String correo;

    private String contrasena;
}
