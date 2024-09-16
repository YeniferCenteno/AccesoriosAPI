package org.esfe.servicios.implementaciones;

import org.esfe.dtos.tipodeaccesorio.TipoDeAccesorioGuardar;
import org.esfe.dtos.tipodeaccesorio.TipoDeAccesorioModificar;
import org.esfe.dtos.tipodeaccesorio.TipoDeAccesorioSalida;
import org.esfe.modelos.TipoDeAccesorio;
import org.esfe.repositorios.ITipoDeAccesorioRepository;
import org.esfe.servicios.interfaces.ITipoDeAccesorioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoDeAccesorioService implements ITipoDeAccesorioService {
    @Autowired
    private ITipoDeAccesorioRepository tipoDeAccesorioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TipoDeAccesorioSalida> obtenerTodos() {
        List<TipoDeAccesorio> tipoDeAccesorios = tipoDeAccesorioRepository.findAll();

        return tipoDeAccesorios.stream()
                .map(tipoDeAccesorio -> modelMapper.map(tipoDeAccesorios, TipoDeAccesorioSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<TipoDeAccesorioSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<TipoDeAccesorio> page = tipoDeAccesorioRepository.findAll(pageable);

        List<TipoDeAccesorioSalida> tipoDeAccesoriosDto = page.stream()
                .map(tipoDeAccesorio -> modelMapper.map(tipoDeAccesorio, TipoDeAccesorioSalida.class))
                .collect(Collectors.toList());

        return new PageImpl<>(tipoDeAccesoriosDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public TipoDeAccesorioSalida obtenerPorId(Integer id) {
        return modelMapper.map(tipoDeAccesorioRepository.findById(id).get(), TipoDeAccesorioSalida.class);
    }

    @Override
    public TipoDeAccesorioSalida crear(TipoDeAccesorioGuardar tipoDeAccesorioGuardar) {
        TipoDeAccesorio tipoDeAccesorio = tipoDeAccesorioRepository.save(modelMapper.map(tipoDeAccesorioGuardar, TipoDeAccesorio.class));
        return modelMapper.map(tipoDeAccesorio, TipoDeAccesorioSalida.class);
    }

    @Override
    public TipoDeAccesorioSalida editar(TipoDeAccesorioModificar tipoDeAccesorioModificar) {
        TipoDeAccesorio tipoDeAccesorio = tipoDeAccesorioRepository.save(modelMapper.map(tipoDeAccesorioModificar, TipoDeAccesorio.class));
        return modelMapper.map(tipoDeAccesorio, TipoDeAccesorioSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        tipoDeAccesorioRepository.deleteById(id);
    }
}
