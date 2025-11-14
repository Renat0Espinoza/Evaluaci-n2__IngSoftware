package com.example.Eval2.Controlador;

import com.example.Eval2.Entidad.Mueble;
import com.example.Eval2.Servicio.MuebleServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/muebles")
public class MuebleController {

    @Autowired
    private MuebleServicio muebleServicio;

    @PostMapping
    public ResponseEntity<Mueble> crearMueble(@RequestBody Mueble mueble) {
        return new ResponseEntity<>(muebleServicio.crearMueble(mueble), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Mueble> listarMuebles() {
        return muebleServicio.listarMuebles();
    }
    
    @GetMapping("/{id}")
    public Mueble buscarMuebleById(@PathVariable Long id) {
        return muebleServicio.buscarMuebleById(id);
    }

    @PutMapping("/{id}")
    public Mueble actualizarMueble(@PathVariable Long id, @RequestBody Mueble mueble) {
        return muebleServicio.actualizarMueble(id, mueble);
    }

    @PatchMapping("/{id}/desactivar")
    public Mueble desactivarMueble(@PathVariable Long id) {
        return muebleServicio.desactivarMueble(id);
    }
}