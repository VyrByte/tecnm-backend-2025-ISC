package mx.tecnm.ecommerce.api. controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web. bind.annotation.*;
import jakarta.validation.Valid;
import mx.tecnm.ecommerce. api.models.DetallesCarrito;
import mx.tecnm.ecommerce.api.models. Producto;
import mx.tecnm. ecommerce.api.repository.DetallesCarritoDAO;
import mx.tecnm. ecommerce.api.repository. ProductoDAO;
import mx.tecnm.ecommerce.api.dto.DetallesCarritoDTO;
import mx.tecnm.ecommerce.api.dto.PUTDetallesCarritoDTO;

@RestController
@RequestMapping("/api/detalles-carrito")
public class DetallesCarritoController {

    @Autowired
    private DetallesCarritoDAO detallesCarritoDAO;

    @Autowired
    private ProductoDAO productoDAO;

    @GetMapping
    public ResponseEntity<List<DetallesCarrito>> obtenerDetallesCarrito() {
        List<DetallesCarrito> detalles = detallesCarritoDAO.obtenerDetallesCarrito();
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallesCarrito> obtenerDetallesCarritoPorId(@PathVariable int id) {
        DetallesCarrito detalle = detallesCarritoDAO.obtenerDetallesCarritoPorId(id);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<DetallesCarrito>> obtenerCarritoPorUsuario(@PathVariable int usuarioId) {
        List<DetallesCarrito> carrito = detallesCarritoDAO.obtenerDetallesCarritoPorUsuario(usuarioId);
        return ResponseEntity.ok(carrito);
    }

    @PostMapping
    public ResponseEntity<DetallesCarrito> agregarAlCarrito(@Valid @RequestBody DetallesCarritoDTO dto) {
        Producto producto = productoDAO.obtenerProductoPorId(dto.productos_id());
        if (producto == null) {
            return ResponseEntity.badRequest().build();
        }

        DetallesCarrito nuevoDetalle = detallesCarritoDAO.insertarDetalleCarrito(dto, producto. precio());
        return ResponseEntity.status(201).body(nuevoDetalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallesCarrito> actualizarDetalleCarrito(@PathVariable int id, @Valid @RequestBody PUTDetallesCarritoDTO dto) {
        DetallesCarrito detalleActualizado = detallesCarritoDAO.actualizarDetalleCarrito(id, dto);
        if (detalleActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalleActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetallesCarrito> eliminarDetalleCarrito(@PathVariable int id) {
        DetallesCarrito detalleEliminado = detallesCarritoDAO.desactivarDetalleCarrito(id);
        if (detalleEliminado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalleEliminado);
    }

    @DeleteMapping("/usuario/{usuarioId}")
    public ResponseEntity<Void> limpiarCarrito(@PathVariable int usuarioId) {
        detallesCarritoDAO.limpiarCarritoPorUsuario(usuarioId);
        return ResponseEntity.noContent().build();
    }
}