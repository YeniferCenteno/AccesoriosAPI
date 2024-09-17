package org.esfe.servicios.implementaciones;

import org.esfe.dtos.accesorio.AccesorioGuardar;
import org.esfe.dtos.accesorio.AccesorioModificar;
import org.esfe.dtos.accesorio.AccesorioSalida;
import org.esfe.modelos.Accesorio;
import org.esfe.repositorios.IAccesorioRepository;
import org.esfe.servicios.interfaces.IAccesorioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccesorioService implements IAccesorioService {
    @Autowired
    private IAccesorioRepository AccesorioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AccesorioSalida> obtenerTodos() {
        List<Accesorio> Accesorio = AccesorioRepository.findAll();

        return Accesorio.stream()
                .map(accesorio -> modelMapper.map(accesorio, AccesorioSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<AccesorioSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Accesorio> page = AccesorioRepository.findAll(pageable);

        List<AccesorioSalida> AccesoriosDto = page.stream()
                .map(Accesorio -> modelMapper.map(Accesorio, AccesorioSalida.class))
                .collect(Collectors.toList());

        return new PageImpl<>(AccesoriosDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public AccesorioSalida obtenerPorId(Integer id) {
        return modelMapper.map(AccesorioRepository.findById(id).get(), AccesorioSalida.class);
    }

    @Override
    public AccesorioSalida crear(AccesorioGuardar AccesorioGuardar) {
        Accesorio Accesorio = AccesorioRepository.save(modelMapper.map(AccesorioGuardar, Accesorio.class));
        return modelMapper.map(Accesorio, AccesorioSalida.class);
    }

    @Override
    public AccesorioSalida editar(AccesorioModificar AccesorioModificar) {
        Accesorio Accesorio = AccesorioRepository.save(modelMapper.map(AccesorioModificar, Accesorio.class));
        return modelMapper.map(Accesorio, AccesorioSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        AccesorioRepository.deleteById(id);
    }
}

