package mx.tecnm.ecommerce.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import mx.tecnm.ecommerce.api.models.EstadoEnvio;

public record EnvioDTO(
        String fecha_entrega,

        @NotNull(message = "La fecha de envío es obligatoria")
        String fecha_envio,

        EstadoEnvio estado,

        @NotNull
        @Min(value = 1, message = "El ID de domicilio es obligatorio")
        Integer domicilios_id,

        @NotNull
        @Min(value = 1, message = "El ID de pedido es obligatorio")
        Integer pedidos_id
) {}