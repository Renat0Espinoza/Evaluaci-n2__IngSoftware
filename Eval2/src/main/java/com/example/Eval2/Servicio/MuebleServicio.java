package com.example.Eval2.Servicio;

import com.example.Eval2.Entidad.EstadoMueble;
import com.example.Eval2.Entidad.Mueble;
import com.example.Eval2.Exception.ResourceNotFoundException;
import com.example.Eval2.Repositorio.MuebleRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuebleServicio {

    @Autowired
    private MuebleRepositorio muebleRepositorio;

    public Mueble crearMueble(Mueble mueble) {
        mueble.setEstado(EstadoMueble.ACTIVO);
        return muebleRepositorio.save(mueble);
    }

    public List<Mueble> listarMuebles() {
        return muebleRepositorio.findAll();
    }
    
    public Mueble buscarMuebleById(Long id) {
        return muebleRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mueble con ID: " + id + " no encontrado"));
    }

    public Mueble actualizarMueble(Long id, Mueble muebleActualizado) {
        Mueble muebleExistente = buscarMuebleById(id);
        muebleExistente.setNombre_mueble(muebleActualizado.getNombre_mueble());
        muebleExistente.setTipo(muebleActualizado.getTipo());
        muebleExistente.setPrecio_base(muebleActualizado.getPrecio_base());
        muebleExistente.setStock(muebleActualizado.getStock());
        muebleExistente.setMaterial(muebleActualizado.getMaterial());
        muebleExistente.setTamaño(muebleActualizado.getTamaño());
        muebleExistente.setEstado(muebleActualizado.getEstado());
        return muebleRepositorio.save(muebleExistente);
    }

    public Mueble desactivarMueble(Long id) {
        Mueble muebleExistente = buscarMuebleById(id);
        muebleExistente.setEstado(EstadoMueble.INACTIVO);
        return muebleRepositorio.save(muebleExistente);
    }
}