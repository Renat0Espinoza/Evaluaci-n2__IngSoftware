package com.example.Eval2.Servicio;

import com.example.Eval2.Entidad.Cotizacion;
import com.example.Eval2.Entidad.CotizacionDetalle;
import com.example.Eval2.Entidad.Mueble;
import com.example.Eval2.Exception.ResourceNotFoundException;
import com.example.Eval2.Exception.StockInsuficienteException;
import com.example.Eval2.Repositorio.CotizacionRepositorio;
import com.example.Eval2.Repositorio.MuebleRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaServicio {

    @Autowired
    private CotizacionRepositorio cotizRepositorio;

    @Autowired
    private MuebleRepositorio muebleRepositorio;

    @Transactional
    public Cotizacion confirmarVenta(Long cotizacion_id) {
        Cotizacion cotizacion = cotizRepositorio.findById(cotizacion_id).orElseThrow(() -> new ResourceNotFoundException("Cotización con ID: " + cotizacion_id +" no encontrada"));

        if ("VENDIDA".equals(cotizacion.getEstado())) {
            throw new RuntimeException("Esta cotización ya fue confirmada como venta.");
        }

        for (CotizacionDetalle detalle : cotizacion.getDetalles()) {
            Mueble mueble = detalle.getMueble();
            if (mueble.getStock() < detalle.getCantidad()) {
                throw new StockInsuficienteException("Stock insuficiente para: " + mueble.getNombre_mueble() + "\nStock disponible: " + mueble.getStock() + "\nSolicitado: " + detalle.getCantidad());
            }
        }

        for (CotizacionDetalle detalle : cotizacion.getDetalles()) {
            Mueble mueble = detalle.getMueble();
            int nuevoStock = mueble.getStock() - detalle.getCantidad();
            mueble.setStock(nuevoStock);
            muebleRepositorio.save(mueble);
        }

        cotizacion.setEstado("VENDIDA");
        return cotizRepositorio.save(cotizacion);
    }
}