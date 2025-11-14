package com.example.Eval2.Entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "cotizacion_detalles")
public class CotizacionDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer cantidad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mueble_id", nullable = false)
    private Mueble mueble;

    @ManyToOne(optional = true)
    @JoinColumn(name = "variante_id", nullable = true)
    private Variante variante;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cotizacion_id", nullable = false)
    private Cotizacion cotizacion;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Mueble getMueble() {
        return mueble;
    }
    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }

    public Variante getVariante() {
        return variante;
    }
    public void setVariante(Variante variante) {
        this.variante = variante;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }
    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }
}