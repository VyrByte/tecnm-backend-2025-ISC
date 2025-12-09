package mx.tecnm.ecommerce.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

public record PUTDetallesPedidoDTO(
        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        int cantidad,

        @DecimalMin(value = "0.0", message = "El precio no puede ser negativo")
        double precio
) {}