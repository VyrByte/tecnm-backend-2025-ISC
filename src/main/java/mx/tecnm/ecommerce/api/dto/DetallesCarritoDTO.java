package mx.tecnm.ecommerce.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetallesCarritoDTO(
        @NotNull
        @Min(value = 1, message = "El ID de producto es obligatorio")
        int productos_id,

        @NotNull
        @Min(value = 1, message = "El ID de usuario es obligatorio")
        int usuarios_id,

        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        int cantidad
) {}