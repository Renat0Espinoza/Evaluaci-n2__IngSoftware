package com.example.Eval2.Controlador;

import com.example.Eval2.Entidad.Cotizacion;
import com.example.Eval2.Servicio.VentaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaServicio ventaServicio;

    @PostMapping("/confirmar/{cotizacionId}")
    public ResponseEntity<Cotizacion> confirmarVenta(@PathVariable Long cotizacionId) {
        Cotizacion cotizacionVendida = ventaServicio.confirmarVenta(cotizacionId);
        return ResponseEntity.ok(cotizacionVendida);
    }
}