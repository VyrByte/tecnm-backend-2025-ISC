package mx.tecnm.ecommerce.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import mx.tecnm.ecommerce.api.models.Producto;
import mx.tecnm.ecommerce.api.repository.ProductoDAO;
import mx.tecnm.ecommerce.api.dto.ProductoDTO;
import mx.tecnm.ecommerce.api.dto.PUTProductoDTO;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoDAO productoDAO;

    @GetMapping
    public ResponseEntity<List<Producto>> obtenerProductos() {
        List<Producto> productos = productoDAO.obtenerProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable int id) {
        Producto producto = productoDAO.obtenerProductoPorId(id);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> insertarProducto(@Valid @RequestBody ProductoDTO dto) {
        Producto nuevoProducto = productoDAO.insertarProducto(dto);
        return ResponseEntity.status(201).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable int id, @Valid @RequestBody PUTProductoDTO dto) {
        Producto productoActualizado = productoDAO.actualizarProducto(id, dto);
        if (productoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> desactivarProducto(@PathVariable int id) {
        Producto productoDesactivado = productoDAO.desactivarProducto(id);
        if (productoDesactivado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoDesactivado);
    }
}