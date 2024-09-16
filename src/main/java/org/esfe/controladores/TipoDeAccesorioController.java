package org.esfe.controladores;

import org.esfe.dtos.tipodeaccesorio.TipoDeAccesorioGuardar;
import org.esfe.dtos.tipodeaccesorio.TipoDeAccesorioModificar;
import org.esfe.dtos.tipodeaccesorio.TipoDeAccesorioSalida;
import org.esfe.servicios.interfaces.ITipoDeAccesorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipoDeAccesorios")
public class TipoDeAccesorioController {
    @Autowired
    private ITipoDeAccesorioService tipoDeAccesorioService;

    @GetMapping
    public ResponseEntity<Page<TipoDeAccesorioSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<TipoDeAccesorioSalida> tipoDeAccesorios = tipoDeAccesorioService.obtenerTodosPaginados(pageable);
        if(tipoDeAccesorios.hasContent()){
            return ResponseEntity.ok(tipoDeAccesorios);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<TipoDeAccesorioSalida>> mostrarTodos(){
        List<TipoDeAccesorioSalida> tipoDeAccesorios = tipoDeAccesorioService.obtenerTodos();
        if(!tipoDeAccesorios.isEmpty()){
            return ResponseEntity.ok(tipoDeAccesorios);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDeAccesorioSalida> buscarPorId(@PathVariable Integer id){
        TipoDeAccesorioSalida tipoDeAccesorio = tipoDeAccesorioService.obtenerPorId(id);

        if(tipoDeAccesorio != null){
            return ResponseEntity.ok(tipoDeAccesorio);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TipoDeAccesorioSalida> crear(@RequestBody TipoDeAccesorioGuardar tipoDeAccesorioGuardar){
        TipoDeAccesorioSalida tipoDeAccesorio = tipoDeAccesorioService.crear(tipoDeAccesorioGuardar);
        return ResponseEntity.ok(tipoDeAccesorio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoDeAccesorioSalida> editar(@PathVariable Integer id, @RequestBody TipoDeAccesorioModificar tipoDeAccesorioModificar){
        TipoDeAccesorioSalida tipoDeAccesorio = tipoDeAccesorioService.editar(tipoDeAccesorioModificar);
        return ResponseEntity.ok(tipoDeAccesorio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar( @PathVariable Integer id){
        tipoDeAccesorioService.eliminarPorId(id);
        return ResponseEntity.ok("Tipo de Acesorio fue eliminado");
    }
}
