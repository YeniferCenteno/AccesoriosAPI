package org.esfe.controladores;

import org.esfe.dtos.accesorio.AccesorioGuardar;
import org.esfe.dtos.accesorio.AccesorioModificar;
import org.esfe.dtos.accesorio.AccesorioSalida;
import org.esfe.servicios.interfaces.IAccesorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accesorios")
public class AccesorioController {
    @Autowired
    private IAccesorioService accesorioService;

    @GetMapping
    public ResponseEntity<Page<AccesorioSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<AccesorioSalida> accesorios = accesorioService.obtenerTodosPaginados(pageable);
        if(accesorios.hasContent()){
            return ResponseEntity.ok(accesorios);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/lista")
    public ResponseEntity<List<AccesorioSalida>> mostrarTodos(){
        List<AccesorioSalida> accesorios = accesorioService.obtenerTodos();
        if(!accesorios.isEmpty()){
            return ResponseEntity.ok(accesorios);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccesorioSalida> buscarPorId(@PathVariable Integer id){
        AccesorioSalida accesorio = accesorioService.obtenerPorId(id);

        if(accesorio != null){
            return ResponseEntity.ok(accesorio);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AccesorioSalida> crear(@RequestBody AccesorioGuardar accesorioGuardar){
        AccesorioSalida accesorio = accesorioService.crear(accesorioGuardar);
        return ResponseEntity.ok(accesorio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccesorioSalida> editar(@PathVariable Integer id, @RequestBody AccesorioModificar accesorioModificar){
        AccesorioSalida accesorio = accesorioService.editar(accesorioModificar);
        return ResponseEntity.ok(accesorio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar( @PathVariable Integer id){
        accesorioService.eliminarPorId(id);
        return ResponseEntity.ok("Acesorio eliminado");
    }
}
