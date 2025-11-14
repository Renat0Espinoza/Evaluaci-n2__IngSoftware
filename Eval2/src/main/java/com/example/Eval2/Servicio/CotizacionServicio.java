package com.example.Eval2.Servicio;

import com.example.Eval2.Entidad.Cotizacion;
import com.example.Eval2.Entidad.CotizacionDetalle;
import com.example.Eval2.Entidad.Mueble;
import com.example.Eval2.Entidad.Variante;
import com.example.Eval2.Exception.ResourceNotFoundException;
import com.example.Eval2.Repositorio.CotizacionRepositorio;
import com.example.Eval2.Repositorio.MuebleRepositorio;
import com.example.Eval2.Repositorio.VarianteRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CotizacionServicio {

    @Autowired
    private CotizacionRepositorio cotizRepositorio;

    @Autowired
    private MuebleRepositorio muebleRepositorio;

    @Autowired
    private VarianteRepositorio variaRepositorio;

    @Transactional
    public Cotizacion crearCotiz(Cotizacion cotizacion) {
        cotizacion.setFecha(LocalDate.now());
        cotizacion.setEstado("PENDIENTE");

        for (CotizacionDetalle detalle : cotizacion.getDetalles()) {
            Mueble mueble = muebleRepositorio.findById(detalle.getMueble().getId_mueble()).orElseThrow(() -> new ResourceNotFoundException("Mueble: " + detalle.getMueble().getId_mueble() + ", no encontrado."));
            
            detalle.setMueble(mueble);

            if (detalle.getVariante() != null && detalle.getVariante().getId() != null) {
                Variante variante = variaRepositorio.findById(detalle.getVariante().getId()).orElseThrow(() -> new ResourceNotFoundException("Variante: " + detalle.getVariante().getId() + ", no encontrada."));
                
                detalle.setVariante(variante);
            } else {
                detalle.setVariante(null);
            }
            detalle.setCotizacion(cotizacion);
        }
        
        return cotizRepositorio.save(cotizacion);
    }

    public List<Cotizacion> listarCotiz() {
        return cotizRepositorio.findAll();
    }

    public Cotizacion buscarCotizById(Long id) {
        return cotizRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cotización no encontrada con ID: " + id));
    }
}