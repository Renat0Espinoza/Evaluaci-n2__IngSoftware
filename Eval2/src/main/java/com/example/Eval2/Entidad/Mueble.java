package com.example.Eval2.Entidad;
import jakarta.persistence.*;

@Entity
@Table(name = "muebles")
public class Mueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mueble;
    private String nombre_mueble;
    private String tipo;
    private Double precio_base;
    private Integer stock;
    private String material;

    @Enumerated(EnumType.STRING) 
    private EstadoMueble estado;

    @Enumerated(EnumType.STRING)
    private TamanoMueble tamaño;

    public Long getId_mueble() {
        return id_mueble;
    }
    public void setId_mueble(Long idMueble) {
        this.id_mueble = idMueble;
    }

    public String getNombre_mueble() {
        return nombre_mueble;
    }
    public void setNombre_mueble(String nombreMueble) {
        this.nombre_mueble = nombreMueble;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecio_base() {
        return precio_base;
    }
    public void setPrecio_base(Double precioBase) {
        this.precio_base = precioBase;
    }

    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }

    public EstadoMueble getEstado() {
        return estado;
    }
    public void setEstado(EstadoMueble estado) {
        this.estado = estado;
    }

    public TamanoMueble getTamaño() {
        return tamaño;
    }
    public void setTamaño(TamanoMueble tamaño) {
        this.tamaño = tamaño;
    }
}