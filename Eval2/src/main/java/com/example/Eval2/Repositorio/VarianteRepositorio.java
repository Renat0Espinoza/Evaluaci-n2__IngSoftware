package com.example.Eval2.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Eval2.Entidad.Variante;

@Repository
public interface VarianteRepositorio extends JpaRepository<Variante, Long> {
}