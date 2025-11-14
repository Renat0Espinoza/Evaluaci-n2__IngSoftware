package com.example.Eval2.Servicio;

import com.example.Eval2.Entidad.EstadoMueble;
import com.example.Eval2.Entidad.Mueble;
import com.example.Eval2.Exception.ResourceNotFoundException;
import com.example.Eval2.Repositorio.MuebleRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MuebleServicioTest {

    @Mock
    private MuebleRepositorio muebleRepositorio;

    @InjectMocks
    private MuebleServicio muebleServicio;

    @Test
    void testCrearMueble() {
        Mueble mueble = new Mueble();
        mueble.setNombre_mueble("Mesa de Centro"); 
        
        Mueble muebleGuardado = new Mueble();
        muebleGuardado.setId_mueble(1L);
        muebleGuardado.setNombre_mueble("Mesa de Centro");
        muebleGuardado.setEstado(EstadoMueble.ACTIVO);

        when(muebleRepositorio.save(any(Mueble.class))).thenReturn(muebleGuardado);

        Mueble resultado = muebleServicio.crearMueble(mueble);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId_mueble());
        assertEquals(EstadoMueble.ACTIVO, resultado.getEstado());
        verify(muebleRepositorio, times(1)).save(any(Mueble.class));
    }

    @Test
    void testDesactivarMueble() {
        Mueble muebleExistente = new Mueble();
        muebleExistente.setId_mueble(1L);
        muebleExistente.setEstado(EstadoMueble.ACTIVO);

        when(muebleRepositorio.findById(1L)).thenReturn(Optional.of(muebleExistente));
        when(muebleRepositorio.save(any(Mueble.class))).thenReturn(muebleExistente);

        Mueble resultado = muebleServicio.desactivarMueble(1L);

        assertNotNull(resultado);
        assertEquals(EstadoMueble.INACTIVO, resultado.getEstado());
        verify(muebleRepositorio, times(1)).save(muebleExistente);
    }

    @Test
    void testBuscarMuebleById_NoEncontrado_LanzaExcepcion() {
        when(muebleRepositorio.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> { muebleServicio.buscarMuebleById(99L); });
    }
}