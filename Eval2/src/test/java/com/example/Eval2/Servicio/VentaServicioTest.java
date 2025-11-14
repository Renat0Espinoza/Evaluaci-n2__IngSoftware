package com.example.Eval2.Servicio;

import com.example.Eval2.Entidad.Cotizacion;
import com.example.Eval2.Entidad.CotizacionDetalle;
import com.example.Eval2.Entidad.Mueble;
import com.example.Eval2.Exception.StockInsuficienteException;
import com.example.Eval2.Repositorio.CotizacionRepositorio;
import com.example.Eval2.Repositorio.MuebleRepositorio;

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
class VentaServicioTest {

    @Mock
    private CotizacionRepositorio cotizRepositorio;
    @Mock
    private MuebleRepositorio muebleRepositorio;
    
    @InjectMocks
    private VentaServicio ventaServicio;

    @Test
    void testConfirmarVenta_StockInsuficiente_LanzaExcepcion() {
        Mueble mueble = new Mueble();
        mueble.setId_mueble(1L);
        mueble.setNombre_mueble("Silla Gamer");
        mueble.setStock(5);

        CotizacionDetalle detalle = new CotizacionDetalle();
        detalle.setMueble(mueble);
        detalle.setCantidad(10);
        
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setId(1L);
        cotizacion.setDetalles(List.of(detalle));
        cotizacion.setEstado("PENDIENTE");

        when(cotizRepositorio.findById(1L)).thenReturn(Optional.of(cotizacion));

        StockInsuficienteException exception = assertThrows(StockInsuficienteException.class, () -> {
            ventaServicio.confirmarVenta(1L);
        });
        
        assertTrue(exception.getMessage().contains("Silla Gamer"));
        
        verify(muebleRepositorio, never()).save(any(Mueble.class));
    }

    @Test
    void testConfirmarVenta_StockSuficiente_DecrementaStockYActualizaEstado() {
        Mueble mueble = new Mueble();
        mueble.setId_mueble(1L);
        mueble.setStock(20);

        CotizacionDetalle detalle = new CotizacionDetalle();
        detalle.setMueble(mueble);
        detalle.setCantidad(5);
        
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setId(1L);
        cotizacion.setDetalles(List.of(detalle));
        cotizacion.setEstado("PENDIENTE");

        when(cotizRepositorio.findById(1L)).thenReturn(Optional.of(cotizacion));
        when(cotizRepositorio.save(any(Cotizacion.class))).thenReturn(cotizacion);

        Cotizacion cotizacionVendida = ventaServicio.confirmarVenta(1L);

        assertEquals(15, mueble.getStock());
        assertEquals("VENDIDA", cotizacionVendida.getEstado());
        
        verify(muebleRepositorio, times(1)).save(mueble);
        verify(cotizRepositorio, times(1)).save(cotizacion);
    }
}