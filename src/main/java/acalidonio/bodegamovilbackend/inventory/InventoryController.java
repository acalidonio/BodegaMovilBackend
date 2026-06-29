package acalidonio.bodegamovilbackend.inventory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<Product>> searchProducts(@RequestParam(required = false) String query) {
        return ResponseEntity.ok(inventoryService.searchProducts(query));
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Product> getProduct(@PathVariable String sku) {
        return inventoryService.getProductBySku(sku)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
