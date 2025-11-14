package com.example.Eval2.Servicio;

import com.example.Eval2.Entidad.Variante;
import com.example.Eval2.Exception.ResourceNotFoundException;
import com.example.Eval2.Repositorio.VarianteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VarianteServicio {

    @Autowired
    private VarianteRepositorio variante_repositorio;

    public Variante crearVariante(Variante variante) {
        return variante_repositorio.save(variante);
    }

    public List<Variante> listarVariantes() {
        return variante_repositorio.findAll();
    }

    public Variante buscarVarById(Long id) {
        return variante_repositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Variante no encontrada con ID: " + id));
    }

    public Variante actualizarVariante(Long id, Variante varianteActualizada) {
        Variante varianteExistente = buscarVarById(id);
        varianteExistente.setNombre(varianteActualizada.getNombre());
        varianteExistente.setCosto_adicional(varianteActualizada.getCosto_adicional());
        return variante_repositorio.save(varianteExistente);
    }

    public void delVariante(Long id) {
        Variante variante = buscarVarById(id);
        variante_repositorio.delete(variante);
    }
}