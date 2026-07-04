package acalidonio.bodegamovilbackend.config;

import acalidonio.bodegamovilbackend.model.Role;
import acalidonio.bodegamovilbackend.model.User;
import acalidonio.bodegamovilbackend.repository.UserRepository;
import acalidonio.bodegamovilbackend.model.Product;
import acalidonio.bodegamovilbackend.repository.ProductRepository;
import acalidonio.bodegamovilbackend.model.StockStatus;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String @NonNull ... args) {
        // Reiniciar la base de datos
        productRepository.deleteAll();
        userRepository.deleteAll();

        String hash = BCrypt.hashpw("password123", BCrypt.gensalt());

        userRepository.save(new User("EMP001",
                "André C",
                "AC",
                Role.ADMIN,
                hash)
        );
        userRepository.save(new User("EMP002",
                "Eduardo C",
                "EC",
                Role.EMPLOYEE,
                hash)
        );

        productRepository.saveAll(List.of(
                    new Product("BRG-6204",
                            "Rodamiento Bolas 6204",
                            "Rodamiento de acero inoxidable para motores eléctricos",
                            StockStatus.AVAILABLE,
                            "Estante A - Nivel 2",
                            150,
                            Date.valueOf("2026-06-20"),
                            "20mm",
                            "47mm",
                            "14mm",
                            "105g",
                            "Acero Inoxidable",
                            true
                    ),
                    new Product("BRG-6205",
                            "Rodamiento Bolas 6205",
                            "Rodamiento de alta velocidad",
                            StockStatus.LOW_STOCK,
                            "Estante A - Nivel 3",
                            4,
                            Date.valueOf("2026-06-15"),
                            "25mm",
                            "52mm",
                            "15mm",
                            "130g",
                            "Acero al Carbono",
                            true
                    ),
                    new Product("BLT-M10X50",
                            "Perno Hexagonal M10x50",
                            "Perno de acero",
                            StockStatus.OUT_OF_STOCK,
                            "Pasillo 3 - Caja 12",
                            0,
                            Date.valueOf("2026-06-25"),
                            null,
                            null,
                            "10mm",
                            "40g",
                            "Acero Inoxidable",
                            true
                    ),
                    new Product("FLG-2IN-150",
                            "Brida 2 pulgadas",
                            "Brida ciega clase 150",
                            StockStatus.AVAILABLE,
                            "Patio Exterior - Lote B",
                            45,
                            Date.valueOf("2026-06-01"),
                            "50mm",
                            "150mm",
                            "20mm",
                            "2kg",
                            "Hierro Fundido",
                            true
                    )
            ));
        System.out.println("Seeder corrió correctamente");
    }
}

