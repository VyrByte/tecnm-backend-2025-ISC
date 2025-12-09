package mx.tecnm.ecommerce.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MetodoPagoDTO(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 25, message = "El nombre no puede tener más de 25 caracteres")
        String nombre,

        @DecimalMin(value = "0.0", message = "La comisión no puede ser negativa")
        float comision
) {}