package mx.tecnm.ecommerce.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import mx.tecnm.ecommerce.api.models.Usuario;
import mx.tecnm.ecommerce.api.repository.UsuarioDAO;
import mx.tecnm.ecommerce.api.dto.UsuarioDTO;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioDAO.obtenerUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable int id) {
        Usuario usuario = usuarioDAO.obtenerUsuarioPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> insertarUsuario(@Valid @RequestBody UsuarioDTO dto) {
        Usuario nuevoUsuario = usuarioDAO.insertarUsuario(dto);
        return ResponseEntity.status(201).body(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @Valid @RequestBody UsuarioDTO dto) {
        Usuario usuarioActualizado = usuarioDAO.actualizarUsuario(id, dto);
        if (usuarioActualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> desactivarUsuario(@PathVariable int id) {
        Usuario usuarioDesactivado = usuarioDAO.desactivarUsuario(id);
        if (usuarioDesactivado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioDesactivado);
    }
}