// src/main/java/mx/tecnm/ecommerce/api/controllers/CategoriaController.java
package mx.tecnm.ecommerce.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web. bind.annotation.*;
import jakarta.validation.Valid;
import mx.tecnm.ecommerce. api.models. Categoria;
import mx.tecnm.ecommerce.api.repository.CategoriaDAO;
import mx.tecnm. ecommerce.api.dto. CategoriaDTO;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaDAO categoriaDAO;

    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerCategorias() {
        List<Categoria> categorias = categoriaDAO.obtenerCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable int id) {
        Categoria categoria = categoriaDAO.obtenerCategoriaPorId(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> insertarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria nuevaCategoria = categoriaDAO. insertarCategoria(categoriaDTO. nombre());
        return ResponseEntity. status(201).body(nuevaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable int id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoriaActualizada = categoriaDAO.actualizarCategoria(id, categoriaDTO.nombre());
        if (categoriaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity. ok(categoriaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> desactivarCategoria(@PathVariable int id) {
        Categoria categoriaDesactivada = categoriaDAO.desactivarCategoria(id);
        if (categoriaDesactivada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoriaDesactivada);
    }
}