package mx.tecnm.ecommerce.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import mx.tecnm.ecommerce.api.models.Envio;
import mx.tecnm.ecommerce.api.repository.EnvioDAO;
import mx.tecnm.ecommerce.api.dto.EnvioDTO;
import mx.tecnm.ecommerce.api.dto.PUTEnvioDTO;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioDAO envioDAO;

    @GetMapping
    public ResponseEntity<List<Envio>> obtenerEnvios() {
        List<Envio> envios = envioDAO.obtenerEnvios();
        return ResponseEntity.ok(envios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> obtenerEnvioPorId(@PathVariable int id) {
        Envio envio = envioDAO.obtenerEnvioPorId(id);
        if (envio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(envio);
    }

    @PostMapping
    public ResponseEntity<Envio> insertarEnvio(@Valid @RequestBody EnvioDTO dto) {
        Envio nuevoEnvio = envioDAO.insertarEnvio(dto);
        return ResponseEntity.status(201).body(nuevoEnvio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable int id, @Valid @RequestBody PUTEnvioDTO dto) {
        Envio envioActualizado = envioDAO.actualizarEnvio(id, dto);
        if (envioActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(envioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Envio> desactivarEnvio(@PathVariable int id) {
        Envio envioDesactivado = envioDAO.desactivarEnvio(id);
        if (envioDesactivado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(envioDesactivado);
    }
}