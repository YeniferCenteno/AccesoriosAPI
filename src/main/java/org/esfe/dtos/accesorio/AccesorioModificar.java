package org.esfe.dtos.accesorio;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.esfe.modelos.TipoDeAccesorio;

import java.io.Serializable;

@Getter
@Setter
public class AccesorioModificar implements Serializable {
    private Integer id;
    private String nombre;
    private Integer tipoDeAccesorioId;
    private String descripcion;
    private String urlFoto;
}
