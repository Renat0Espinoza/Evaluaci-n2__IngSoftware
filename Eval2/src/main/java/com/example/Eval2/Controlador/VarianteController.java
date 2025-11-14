package com.example.Eval2.Controlador;

import com.example.Eval2.Entidad.Variante;
import com.example.Eval2.Servicio.VarianteServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variantes")
public class VarianteController {

    @Autowired
    private VarianteServicio varianteServicio;

    @PostMapping
    public ResponseEntity<Variante> crearVariante(@RequestBody Variante variante) {
        return new ResponseEntity<>(varianteServicio.crearVariante(variante), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Variante> listarVariantes() {
        return varianteServicio.listarVariantes();
    }

    @GetMapping("/{id}")
    public Variante buscarVarById(@PathVariable Long id) {
        return varianteServicio.buscarVarById(id);
    }

    @PutMapping("/{id}")
    public Variante actualizarVariante(@PathVariable Long id, @RequestBody Variante variante) {
        return varianteServicio.actualizarVariante(id, variante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delVariante(@PathVariable Long id) {
        varianteServicio.delVariante(id);
        return ResponseEntity.noContent().build();
    }
}