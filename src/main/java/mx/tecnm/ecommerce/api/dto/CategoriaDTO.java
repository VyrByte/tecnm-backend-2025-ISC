package mx.tecnm.ecommerce.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaDTO(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 40, message = "El nombre no puede tener más de 40 caracteres")
        String nombre
) {}