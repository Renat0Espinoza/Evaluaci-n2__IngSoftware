package com.example.Eval2.Controlador;

import com.example.Eval2.Entidad.Cotizacion;
import com.example.Eval2.Servicio.CotizacionServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionController {

    @Autowired
    private CotizacionServicio cotizServicio;

    @PostMapping
    public ResponseEntity<Cotizacion> crearCotiz(@RequestBody Cotizacion cotizacion) {
        Cotizacion newCotiz = cotizServicio.crearCotiz(cotizacion);
        return new ResponseEntity<>(newCotiz, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Cotizacion> listarCotiz() {
        return cotizServicio.listarCotiz();
    }

    @GetMapping("/{id}")
    public Cotizacion buscarCotizById(@PathVariable Long id) {
        return cotizServicio.buscarCotizById(id);
    }
}