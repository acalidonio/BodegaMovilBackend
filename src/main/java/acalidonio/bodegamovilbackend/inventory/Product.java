package acalidonio.bodegamovilbackend.inventory;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    private String sku;
    
    private String name;
    private String description;

    private StockStatus status;
    
    private String location;
    private int stock;
    
    private String lastAudit;
    
    // Technical specs
    private String innerDiameter;
    private String outerDiameter;
    private String width;
    private String weight;
    private String material;
}
