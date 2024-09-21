package org.esfe.dtos.accesorio;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.esfe.modelos.TipoDeAccesorio;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
public class AccesorioGuardar implements Serializable{
    private String nombre;
    private Integer tipoDeAccesorioId;
    private String descripcion;
    private MultipartFile file;
}
