package acalidonio.bodegamovilbackend.service.impl;

import acalidonio.bodegamovilbackend.dto.ProductDto;
import acalidonio.bodegamovilbackend.model.Product;
import acalidonio.bodegamovilbackend.repository.ProductRepository;
import acalidonio.bodegamovilbackend.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findByActiveTrue().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<ProductDto> searchProducts(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllProducts();
        }
        return productRepository.searchProducts(query).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<ProductDto> getProductBySku(String sku) {
        Optional<Product> p = productRepository.findById(sku);

        if (p.isPresent() && p.get().isActive()) {
            return Optional.of(mapToDto(p.get()));
        }
        return Optional.empty();
    }
    
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        product.setActive(true);

        Product savedProduct = productRepository.save(product);
        return mapToDto(savedProduct);
    }
    
    @Override
    public ProductDto updateProduct(String sku, ProductDto productDto) {
        Optional<Product> productOpt = productRepository.findById(sku);

        if (productOpt.isPresent() && productOpt.get().isActive()) {
            Product existingProduct = productOpt.get();

            existingProduct.setName(productDto.getName());
            existingProduct.setDescription(productDto.getDescription());
            existingProduct.setStatus(productDto.getStatus());
            existingProduct.setLocation(productDto.getLocation());
            existingProduct.setStock(productDto.getStock());
            existingProduct.setLastAudit(productDto.getLastAudit());
            existingProduct.setInnerDiameter(productDto.getInnerDiameter());
            existingProduct.setOuterDiameter(productDto.getOuterDiameter());
            existingProduct.setWidth(productDto.getWidth());
            existingProduct.setWeight(productDto.getWeight());
            existingProduct.setMaterial(productDto.getMaterial());

            Product updatedProduct = productRepository.save(existingProduct);
            return mapToDto(updatedProduct);
        }
        throw new RuntimeException("Producto no encontrado o inactivo");
    }
    
    @Override
    public void deleteProduct(String sku) {
        Optional<Product> productOpt = productRepository.findById(sku);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setActive(false);
            productRepository.save(product);
        }
    }

    private ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())
                .status(product.getStatus())
                .location(product.getLocation())
                .stock(product.getStock())
                .lastAudit(product.getLastAudit())
                .innerDiameter(product.getInnerDiameter())
                .outerDiameter(product.getOuterDiameter())
                .width(product.getWidth())
                .weight(product.getWeight())
                .material(product.getMaterial())
                .build();
    }

    private Product mapToEntity(ProductDto dto) {
        return Product.builder()
                .sku(dto.getSku())
                .name(dto.getName())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .location(dto.getLocation())
                .stock(dto.getStock())
                .lastAudit(dto.getLastAudit())
                .innerDiameter(dto.getInnerDiameter())
                .outerDiameter(dto.getOuterDiameter())
                .width(dto.getWidth())
                .weight(dto.getWeight())
                .material(dto.getMaterial())
                .build();
    }
}
