package mx.tecnm.ecommerce.api.dto;

import mx.tecnm.ecommerce.api.models.EstadoEnvio;

public record PUTEnvioDTO(
        String fecha_entrega,
        String fecha_envio,
        EstadoEnvio estado,
        Integer domicilios_id,
        Integer pedidos_id
) {}