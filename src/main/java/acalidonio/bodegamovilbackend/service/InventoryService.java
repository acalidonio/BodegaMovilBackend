package acalidonio.bodegamovilbackend.service;

import acalidonio.bodegamovilbackend.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    List<ProductDto> getAllProducts();
    List<ProductDto> searchProducts(String query);
    Optional<ProductDto> getProductBySku(String sku);

    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(String sku, ProductDto productDto);
    void deleteProduct(String sku);
}
