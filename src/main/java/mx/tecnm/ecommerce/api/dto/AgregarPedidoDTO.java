package mx.tecnm.ecommerce.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AgregarPedidoDTO(
        @NotNull
        @Min(value = 1, message = "El ID de usuario es obligatorio")
        int usuarios_id,

        @DecimalMin(value = "0.0", message = "El importe de envío no puede ser negativo")
        double importe_envio,

        @NotNull
        @Min(value = 1, message = "El método de pago es obligatorio")
        int metodos_pago_id
) {}