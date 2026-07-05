package acalidonio.bodegamovilbackend.repository;

import acalidonio.bodegamovilbackend.domain.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Page<Product> findByActiveTrue(Pageable pageable);

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE p.active = true " +
            "AND (LOWER(p.sku) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')))"
    )
    Page<Product> searchProducts(@Param("query") String query, Pageable pageable);

    boolean existsByNameIgnoreCase(String value);

    boolean existsBySkuIgnoreCase(String value);
}
