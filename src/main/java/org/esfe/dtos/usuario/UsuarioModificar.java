package org.esfe.dtos.usuario;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UsuarioModificar implements Serializable {
    private String correo;

    private String contrasena;
}
