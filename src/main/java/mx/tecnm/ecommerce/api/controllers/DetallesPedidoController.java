package mx.tecnm.ecommerce.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import mx.tecnm.ecommerce.api.models.DetallesPedido;
import mx.tecnm.ecommerce.api.repository.DetallesPedidoDAO;
import mx.tecnm.ecommerce.api.dto.DetallesPedidoDTO;
import mx.tecnm.ecommerce.api.dto.PUTDetallesPedidoDTO;

@RestController
@RequestMapping("/api/detalles-pedido")
public class DetallesPedidoController {

    @Autowired
    private DetallesPedidoDAO detallesPedidoDAO;

    @GetMapping
    public ResponseEntity<List<DetallesPedido>> obtenerDetallesPedido() {
        List<DetallesPedido> detalles = detallesPedidoDAO.obtenerDetallesPedido();
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallesPedido> obtenerDetallesPedidoPorId(@PathVariable int id) {
        DetallesPedido detalle = detallesPedidoDAO.obtenerDetallesPedidoPorId(id);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<DetallesPedido>> obtenerDetallesPorPedido(@PathVariable int pedidoId) {
        List<DetallesPedido> detalles = detallesPedidoDAO.obtenerDetallesPorPedido(pedidoId);
        return ResponseEntity.ok(detalles);
    }

    @PostMapping
    public ResponseEntity<DetallesPedido> insertarDetallePedido(@Valid @RequestBody DetallesPedidoDTO dto) {
        DetallesPedido nuevoDetalle = detallesPedidoDAO.insertarDetallePedido(dto);
        return ResponseEntity.status(201).body(nuevoDetalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallesPedido> actualizarDetallePedido(@PathVariable int id, @Valid @RequestBody PUTDetallesPedidoDTO dto) {
        DetallesPedido detalleActualizado = detallesPedidoDAO.actualizarDetallePedido(id, dto);
        if (detalleActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalleActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetallesPedido> desactivarDetallePedido(@PathVariable int id) {
        DetallesPedido detalleDesactivado = detallesPedidoDAO.desactivarDetallePedido(id);
        if (detalleDesactivado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalleDesactivado);
    }
}