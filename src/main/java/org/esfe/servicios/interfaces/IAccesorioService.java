package org.esfe.servicios.interfaces;

import org.esfe.dtos.accesorio.AccesorioGuardar;
import org.esfe.dtos.accesorio.AccesorioModificar;
import org.esfe.dtos.accesorio.AccesorioSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccesorioService {

    List<AccesorioSalida> obtenerTodos();

    Page<AccesorioSalida> obtenerTodosPaginados(Pageable pageable);

    AccesorioSalida obtenerPorId(Integer id);

    AccesorioSalida crear(AccesorioGuardar AccesorioGuardar);

    AccesorioSalida editar(AccesorioModificar AccesorioModificar);

    void eliminarPorId(Integer id);
}