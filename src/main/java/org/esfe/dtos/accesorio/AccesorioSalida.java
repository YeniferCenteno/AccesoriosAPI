package org.esfe.dtos.accesorio;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.esfe.modelos.TipoDeAccesorio;
import org.esfe.dtos.tipodeaccesorio.TipoDeAccesorioSalida;

import java.io.Serializable;

@Getter
@Setter
public class AccesorioSalida implements Serializable {

    private Integer id;
    private String nombre;
    private TipoDeAccesorioSalida tipoDeAccesorio;
    private String descripcion;
    private String urlFoto;
}
