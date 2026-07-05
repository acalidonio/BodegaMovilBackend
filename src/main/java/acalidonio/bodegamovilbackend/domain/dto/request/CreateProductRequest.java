package acalidonio.bodegamovilbackend.domain.dto.request;

import acalidonio.bodegamovilbackend.common.validations.annotations.UniqueProductName;
import acalidonio.bodegamovilbackend.common.validations.annotations.UniqueProductSku;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotBlank(message = "El SKU es requerido")
    @UniqueProductSku
    private String sku;

    @NotBlank(message = "El nombre es requerido")
    @UniqueProductName
    private String name;

    private String description;

    private String location;

    @NotNull(message = "El stock es requerido")
    @PositiveOrZero(message = "El stock debe ser mayor o igual a 0")
    private Integer stock;

    private String innerDiameter;
    private String outerDiameter;
    private String width;
    private String weight;
    private String material;
}
