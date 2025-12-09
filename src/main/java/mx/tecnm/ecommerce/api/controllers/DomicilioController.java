package mx.tecnm.ecommerce.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import mx.tecnm.ecommerce.api.models.Domicilio;
import mx.tecnm.ecommerce.api.repository.DomicilioDAO;
import mx.tecnm.ecommerce.api.dto.DomicilioDTO;

@RestController
@RequestMapping("/api/domicilios")
public class DomicilioController {

    @Autowired
    private DomicilioDAO domicilioDAO;

    @GetMapping
    public ResponseEntity<List<Domicilio>> obtenerDomicilios() {
        List<Domicilio> domicilios = domicilioDAO.obtenerDomicilios();
        return ResponseEntity.ok(domicilios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Domicilio> obtenerDomicilioPorId(@PathVariable int id) {
        Domicilio domicilio = domicilioDAO.obtenerDomiciliosPorId(id);
        if (domicilio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(domicilio);
    }

    @PostMapping
    public ResponseEntity<Domicilio> insertarDomicilio(@Valid @RequestBody DomicilioDTO dto) {
        Domicilio nuevoDomicilio = domicilioDAO.insertarDomicilio(dto);
        return ResponseEntity.status(201).body(nuevoDomicilio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Domicilio> actualizarDomicilio(@PathVariable int id, @Valid @RequestBody DomicilioDTO dto) {
        Domicilio domicilioActualizado = domicilioDAO.actualizarDomicilio(id, dto);
        if (domicilioActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(domicilioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Domicilio> desactivarDomicilio(@PathVariable int id) {
        Domicilio domicilioDesactivado = domicilioDAO.desactivarDomicilio(id);
        if (domicilioDesactivado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(domicilioDesactivado);
    }
}