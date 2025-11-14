package com.example.Eval2.Entidad;
import jakarta.persistence.*;

@Entity
@Table(name = "variantes")
public class Variante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre; 

    @Column(nullable = false)
    private Double costo_adicional;

    public Variante() {
    }

    public Variante(String nombre, Double costo_adicional) {
        this.nombre = nombre;
        this.costo_adicional = costo_adicional;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCosto_adicional() {
        return costo_adicional;
    }
    public void setCosto_adicional(Double costoAdicional) {
        this.costo_adicional = costoAdicional;
    }
}