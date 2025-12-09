package mx.tecnm.ecommerce.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductoDTO(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 60, message = "El nombre no puede tener más de 60 caracteres")
        String nombre,

        @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
        float precio,

        @NotBlank(message = "El SKU es obligatorio")
        @Size(max = 15, message = "El SKU no puede tener más de 15 caracteres")
        String sku,

        @Size(max = 15)
        String color,

        @NotBlank(message = "La marca es obligatoria")
        @Size(max = 20, message = "La marca no puede tener más de 20 caracteres")
        String marca,

        String descripcion,

        @DecimalMin(value = "0.0", message = "El peso no puede ser negativo")
        float peso,

        float alto,
        float ancho,
        float profundidad,

        @NotNull(message = "La categoría es obligatoria")
        @Min(value = 1, message = "ID de categoría inválido")
        int categorias_id
) {}