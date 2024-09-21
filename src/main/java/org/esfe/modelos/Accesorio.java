package org.esfe.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "accesorios")
public class Accesorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "tipoDeAccesorio_id")
    private TipoDeAccesorio tipoDeAccesorio;

    private String descripcion;

    @Column(name = "url_image")
    private String urlFoto;

}
