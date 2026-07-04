package acalidonio.bodegamovilbackend.controller;

import acalidonio.bodegamovilbackend.dto.ProductDto;
import acalidonio.bodegamovilbackend.service.InventoryService;

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
    public ResponseEntity<List<ProductDto>> getInventory(@RequestParam(required = false) String query) {
        if (query != null && !query.isEmpty()) {
            return ResponseEntity.ok(inventoryService.searchProducts(query));
        }
        return ResponseEntity.ok(inventoryService.getAllProducts());
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String sku) {
        return inventoryService.getProductBySku(sku)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto created = inventoryService.createProduct(productDto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{sku}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String sku, @RequestBody ProductDto productDto) {
        try {
            ProductDto updated = inventoryService.updateProduct(sku, productDto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String sku) {
        inventoryService.deleteProduct(sku);
        return ResponseEntity.noContent().build();
    }
}

