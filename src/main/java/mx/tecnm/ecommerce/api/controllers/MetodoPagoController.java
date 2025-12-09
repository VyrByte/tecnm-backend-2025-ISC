package mx.tecnm.ecommerce.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import mx.tecnm.ecommerce.api.models.MetodoPago;
import mx.tecnm.ecommerce.api.repository.MetodoPagoDAO;
import mx.tecnm.ecommerce.api.dto.MetodoPagoDTO;

@RestController
@RequestMapping("/api/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoDAO metodoPagoDAO;

    @GetMapping
    public ResponseEntity<List<MetodoPago>> obtenerMetodosPago() {
        List<MetodoPago> metodos = metodoPagoDAO.obtenerMetodosPago();
        return ResponseEntity.ok(metodos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> obtenerMetodoPagoPorId(@PathVariable int id) {
        MetodoPago metodo = metodoPagoDAO.obtenerMetodoPagoPorId(id);
        if (metodo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(metodo);
    }

    @PostMapping
    public ResponseEntity<MetodoPago> insertarMetodoPago(@Valid @RequestBody MetodoPagoDTO dto) {
        MetodoPago nuevoMetodo = metodoPagoDAO.insertarMetodoPago(dto);
        return ResponseEntity.status(201).body(nuevoMetodo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> actualizarMetodoPago(@PathVariable int id, @Valid @RequestBody MetodoPagoDTO dto) {
        MetodoPago metodoActualizado = metodoPagoDAO.actualizarMetodoPago(id, dto);
        if (metodoActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(metodoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MetodoPago> desactivarMetodoPago(@PathVariable int id) {
        MetodoPago metodoDesactivado = metodoPagoDAO.desactivarMetodoPago(id);
        if (metodoDesactivado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(metodoDesactivado);
    }
}