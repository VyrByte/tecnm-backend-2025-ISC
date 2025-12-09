package mx.tecnm.ecommerce.api.dto;

import jakarta.validation.constraints.DecimalMin;

public record PUTPedidoDTO(
        @DecimalMin(value = "0.0", message = "El importe de productos no puede ser negativo")
        double importe_productos,

        @DecimalMin(value = "0.0", message = "El importe de envío no puede ser negativo")
        double importe_envio
) {}