package acalidonio.bodegamovilbackend.inventory;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public List<Product> searchProducts(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllProducts();
        }
        return productRepository.searchProducts(query);
    }
    
    public Optional<Product> getProductBySku(String sku) {
        return productRepository.findById(sku);
    }
}
