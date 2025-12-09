package mx.tecnm.ecommerce.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import mx.tecnm.ecommerce.api.models.Pedido;
import mx.tecnm.ecommerce.api.models.DetallesCarrito;
import mx.tecnm.ecommerce.api.repository.PedidoDAO;
import mx.tecnm.ecommerce.api.repository.DetallesCarritoDAO;
import mx.tecnm.ecommerce.api.repository.DetallesPedidoDAO;
import mx.tecnm.ecommerce.api.dto.AgregarPedidoDTO;
import mx.tecnm.ecommerce.api.dto.PUTPedidoDTO;
import mx.tecnm.ecommerce.api.dto.DetallesPedidoDTO;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoDAO pedidoDAO;

    @Autowired
    private DetallesCarritoDAO detallesCarritoDAO;

    @Autowired
    private DetallesPedidoDAO detallesPedidoDAO;

    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerPedidos() {
        List<Pedido> pedidos = pedidoDAO.obtenerPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable int id) {
        Pedido pedido = pedidoDAO.obtenerPedidoPorId(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> procesarPedido(@Valid @RequestBody AgregarPedidoDTO dto) {
        // Obtener el carrito del usuario
        List<DetallesCarrito> carrito = detallesCarritoDAO.obtenerDetallesCarritoPorUsuario(dto.usuarios_id());

        if (carrito.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Calcular importe total de productos
        double importeProductos = carrito.stream()
                .mapToDouble(item -> item.precio() * item.cantidad())
                .sum();

        // Crear el pedido
        Pedido nuevoPedido = pedidoDAO.insertarPedido(dto, importeProductos);

        // Transferir items del carrito a detalles_pedido
        for (DetallesCarrito item : carrito) {
            DetallesPedidoDTO detalle = new DetallesPedidoDTO(
                    nuevoPedido.id(),
                    item.productos_id(),
                    item.cantidad(),
                    item.precio()
            );
            detallesPedidoDAO.insertarDetallePedido(detalle);
        }

        // Limpiar el carrito
        detallesCarritoDAO.limpiarCarritoPorUsuario(dto.usuarios_id());

        return ResponseEntity.status(201).body(nuevoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable int id, @Valid @RequestBody PUTPedidoDTO dto) {
        Pedido pedidoActualizado = pedidoDAO.actualizarPedido(id, dto);
        if (pedidoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pedido> desactivarPedido(@PathVariable int id) {
        Pedido pedidoDesactivado = pedidoDAO.desactivarPedido(id);
        if (pedidoDesactivado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoDesactivado);
    }
}