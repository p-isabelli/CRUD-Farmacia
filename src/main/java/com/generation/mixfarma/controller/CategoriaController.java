package com.generation.mixfarma.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.mixfarma.model.Categoria;
import com.generation.mixfarma.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @GetMapping
    public ResponseEntity<List<Categoria>> getAll(){
        return ResponseEntity.ok(categoriaRepository.findAll());
    }
    
    @GetMapping("/{idcat}")
    public ResponseEntity<Categoria> getById(@PathVariable Long idcat){
        return categoriaRepository.findById(idcat)
            .map(resposta -> ResponseEntity.ok(resposta))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @GetMapping("categoria/{categoria}")
	public ResponseEntity <List<Categoria>> getByTitle(@PathVariable String categoria){
		return ResponseEntity.ok(categoriaRepository.findAllByCategoriaContainingIgnoreCase(categoria));
	}
    
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Categoria>> getByString(@PathVariable 
    String descricao){
        return ResponseEntity.ok(categoriaRepository
            .findAllByDescricaoContainingIgnoreCase(descricao));
    }
    
    @PostMapping
    public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoriaRepository.save(categoria));
    }
    
    @PutMapping
    public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria){
        return categoriaRepository.findById(categoria.getIdcat())
            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
            .body(categoriaRepository.save(categoria)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{idcat}")
    public void delete(@PathVariable Long id) {
        Optional<Categoria> tema = categoriaRepository.findById(id);
        
        if(tema.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        categoriaRepository.deleteById(id);              
    }

}