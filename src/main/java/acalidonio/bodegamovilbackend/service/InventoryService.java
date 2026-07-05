package acalidonio.bodegamovilbackend.service;

import acalidonio.bodegamovilbackend.domain.dto.request.CreateProductRequest;
import acalidonio.bodegamovilbackend.domain.dto.request.UpdateProductRequest;
import acalidonio.bodegamovilbackend.domain.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryService {
    Page<ProductResponse> getAllProducts(Pageable pageable);
    Page<ProductResponse> searchProducts(String query, Pageable pageable);
    ProductResponse getProductBySku(String sku);

    ProductResponse createProduct(CreateProductRequest request);
    ProductResponse updateProduct(String sku, UpdateProductRequest request);
    void deleteProduct(String sku);
    void restoreProduct(String sku);
}
