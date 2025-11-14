package com.example.Eval2.Servicio;

import com.example.Eval2.Entidad.*;
import com.example.Eval2.Repositorio.CotizacionRepositorio;
import com.example.Eval2.Repositorio.MuebleRepositorio;
import com.example.Eval2.Repositorio.VarianteRepositorio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CotizacionServicioTest {

    @Mock
    private CotizacionRepositorio cotizRepositorio;
    @Mock
    private MuebleRepositorio muebleRepositorio;
    @Mock
    private VarianteRepositorio variaRepositorio;
    
    @InjectMocks
    private CotizacionServicio cotizacionServicio;

    @Test
    void testCrearCotiz() {
        Mueble muebleBase = new Mueble();
        muebleBase.setId_mueble(1L);
        muebleBase.setPrecio_base(100.0);
        
        Mueble muebleCaro = new Mueble();
        muebleCaro.setId_mueble(2L);
        muebleCaro.setPrecio_base(500.0);

        Variante variantePremium = new Variante();
        variantePremium.setId(1L);
        CotizacionDetalle detalle1 = new CotizacionDetalle();
        detalle1.setCantidad(2);
        Mueble mockMueble1 = new Mueble();
        mockMueble1.setId_mueble(1L);
        detalle1.setMueble(mockMueble1);
        detalle1.setVariante(null);

        CotizacionDetalle detalle2 = new CotizacionDetalle();
        detalle2.setCantidad(1);
        Mueble mockMueble2 = new Mueble();
        mockMueble2.setId_mueble(2L);
        detalle2.setMueble(mockMueble2);
        Variante mockVariante = new Variante();
        mockVariante.setId(1L);
        detalle2.setVariante(mockVariante);
        
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setDetalles(List.of(detalle1, detalle2));

        when(muebleRepositorio.findById(1L)).thenReturn(Optional.of(muebleBase));
        when(muebleRepositorio.findById(2L)).thenReturn(Optional.of(muebleCaro));
        when(variaRepositorio.findById(1L)).thenReturn(Optional.of(variantePremium));
        when(cotizRepositorio.save(any(Cotizacion.class))).thenReturn(cotizacion);

        Cotizacion cotizacionGuardada = cotizacionServicio.crearCotiz(cotizacion);

        assertNotNull(cotizacionGuardada);
        assertNull(cotizacionGuardada.getDetalles().get(0).getVariante());
        assertEquals(variantePremium, cotizacionGuardada.getDetalles().get(1).getVariante());
        assertEquals("PENDIENTE", cotizacionGuardada.getEstado());
        verify(cotizRepositorio, times(1)).save(cotizacion);
    }
}