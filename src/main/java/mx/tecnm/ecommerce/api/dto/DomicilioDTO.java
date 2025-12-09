package mx.tecnm.ecommerce.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DomicilioDTO(
        @NotBlank(message = "La calle es obligatoria")
        @Size(max = 45)
        String calle,

        @NotBlank(message = "El número es obligatorio")
        @Size(max = 10)
        String numero,

        @NotBlank(message = "La colonia es obligatoria")
        @Size(max = 30)
        String colonia,

        @NotBlank(message = "El código postal es obligatorio")
        @Size(min = 5, max = 5, message = "El CP debe tener 5 caracteres")
        String cp,

        @NotBlank(message = "El estado es obligatorio")
        @Size(max = 20)
        String estado,

        @NotBlank(message = "La ciudad es obligatoria")
        @Size(max = 45)
        String ciudad,

        @NotNull(message = "El ID de usuario es obligatorio")
        @Min(value = 1)
        int usuarios_id
) {}