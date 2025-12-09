package mx.tecnm.ecommerce.api.dto;

import jakarta.validation.constraints.Min;

public record UsuarioIdDTO(
        @Min(value = 1, message = "El ID de usuario debe ser válido")
        int UsuarioId
) {}