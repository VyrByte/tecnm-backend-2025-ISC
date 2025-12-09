package mx.tecnm.ecommerce.api.dto;

import jakarta.validation.constraints.Min;

public record PUTDetallesCarritoDTO(
        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        int cantidad,

        int productos_id,
        int usuarios_id
) {}