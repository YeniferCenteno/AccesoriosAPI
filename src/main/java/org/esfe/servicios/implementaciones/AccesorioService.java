package org.esfe.servicios.implementaciones;

import com.cloudinary.Cloudinary;
import jakarta.annotation.Resource;
import org.esfe.dtos.accesorio.AccesorioGuardar;
import org.esfe.dtos.accesorio.AccesorioModificar;
import org.esfe.dtos.accesorio.AccesorioSalida;
import org.esfe.modelos.Accesorio;
import org.esfe.modelos.TipoDeAccesorio;
import org.esfe.repositorios.IAccesorioRepository;
import org.esfe.repositorios.ITipoDeAccesorioRepository;
import org.esfe.servicios.interfaces.IAccesorioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccesorioService implements IAccesorioService {
    @Autowired
    private IAccesorioRepository accesorioRepository;

    @Autowired
    private ITipoDeAccesorioRepository tipoDeAccesorioRepository;

    @Resource
    private Cloudinary cloudinary;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AccesorioSalida> obtenerTodos() {
        List<Accesorio> Accesorio = accesorioRepository.findAll();

        return Accesorio.stream()
                .map(accesorio -> modelMapper.map(accesorio, AccesorioSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<AccesorioSalida> obtenerTodosPaginados(String nombre, Pageable pageable) {
        Page<Accesorio> page = accesorioRepository.findByNombreContaining(nombre, pageable);

        List<AccesorioSalida> AccesoriosDto = page.stream()
                .map(Accesorio -> modelMapper.map(Accesorio, AccesorioSalida.class))
                .collect(Collectors.toList());

        return new PageImpl<>(AccesoriosDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public AccesorioSalida obtenerPorId(Integer id) {
        return modelMapper.map(accesorioRepository.findById(id).get(), AccesorioSalida.class);
    }

    @Override
    public AccesorioSalida crear(AccesorioGuardar accesorioGuardar) {
        try{
           Accesorio accesorio = new Accesorio();
           accesorio.setUrlFoto(this.uploadFile(accesorioGuardar.getFile(), "accesorios"));
           accesorio.setDescripcion(accesorioGuardar.getDescripcion());
            TipoDeAccesorio tipoDeAccesorio = tipoDeAccesorioRepository.findById(accesorioGuardar.getTipoDeAccesorioId())
                    .orElseThrow(() -> new RuntimeException("Tipo de accesorio no encontrado"));
           accesorio.setTipoDeAccesorio(tipoDeAccesorio);
           accesorio.setNombre(accesorioGuardar.getNombre());

           return modelMapper.map(accesorioRepository.save(accesorio), AccesorioSalida.class);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AccesorioSalida editar(AccesorioModificar AccesorioModificar) {
        Accesorio Accesorio = accesorioRepository.save(modelMapper.map(AccesorioModificar, Accesorio.class));
        return modelMapper.map(Accesorio, AccesorioSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        accesorioRepository.deleteById(id);
    }

    @Override
    public String uploadFile(MultipartFile file, String folderName) {
        try{
            HashMap<Object, Object> options = new HashMap<>();
            options.put("folder", folderName);
            Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
            String publicId = (String) uploadedFile.get("public_id");
            return cloudinary.url().secure(true).generate(publicId);

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}

