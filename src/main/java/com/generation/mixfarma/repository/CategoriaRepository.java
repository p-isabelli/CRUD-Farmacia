package com.generation.mixfarma.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.mixfarma.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

   public List<Categoria> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
   
   public List<Categoria> findAllByCategoriaContainingIgnoreCase (@Param("categoria")String categoria);


}