package mx.tecnm.ecommerce.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DetallesPedidoDTO(
        @NotNull
        @Min(value = 1, message = "El ID de pedido es obligatorio")
        int pedidos_id,

        @NotNull
        @Min(value = 1, message = "El ID de producto es obligatorio")
        int productos_id,

        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        int cantidad,

        @DecimalMin(value = "0.0", message = "El precio no puede ser negativo")
        double precio
) {}