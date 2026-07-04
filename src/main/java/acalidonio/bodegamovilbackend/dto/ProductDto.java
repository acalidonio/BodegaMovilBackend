package acalidonio.bodegamovilbackend.dto;

import acalidonio.bodegamovilbackend.model.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private String sku;
    private String name;
    private String description;
    private StockStatus status;
    private String location;
    private int stock;
    private Date lastAudit;
    
    // Technical specs
    private String innerDiameter;
    private String outerDiameter;
    private String width;
    private String weight;
    private String material;
}
