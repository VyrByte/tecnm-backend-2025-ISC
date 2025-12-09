// src/main/java/mx/tecnm/ecommerce/api/dto/UsuarioDTO.java
package mx.tecnm.ecommerce.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import mx.tecnm.ecommerce.api.models.Sexo;

import java.time.LocalDate;

public record UsuarioDTO(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 45, message = "El nombre no puede tener más de 45 caracteres")
        String nombre,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Debe ser un email válido")
        @Size(max = 60, message = "El email no puede tener más de 60 caracteres")
        String email,

        @NotBlank(message = "El teléfono es obligatorio")
        @Size(min = 10, max = 10, message = "El teléfono debe tener 10 dígitos")
        String telefono,

        @NotNull(message = "El sexo es obligatorio")
        Sexo sexo,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        LocalDate fecha_nacimiento,

        @NotBlank(message = "La contraseña es obligatoria")
        String contrasena
) {}