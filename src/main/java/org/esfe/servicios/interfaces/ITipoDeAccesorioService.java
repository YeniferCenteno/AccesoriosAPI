package org.esfe.servicios.interfaces;

import org.esfe.dtos.tipodeaccesorio.TipoDeAccesorioGuardar;
import org.esfe.dtos.tipodeaccesorio.TipoDeAccesorioModificar;
import org.esfe.dtos.tipodeaccesorio.TipoDeAccesorioSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ITipoDeAccesorioService {
    List<TipoDeAccesorioSalida> obtenerTodos();

    Page<TipoDeAccesorioSalida> obtenerTodosPaginados(Pageable pageable);

    TipoDeAccesorioSalida obtenerPorId(Integer id);

    TipoDeAccesorioSalida crear(TipoDeAccesorioGuardar tipoDeAccesorioGuardar);

    TipoDeAccesorioSalida editar(TipoDeAccesorioModificar tipoDeAccesorioModificar);

    void eliminarPorId(Integer id);

}
