package acalidonio.bodegamovilbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    @Id
    private String employeeId;
    
    private String name;

    private String initials;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private String passwordHash;
}

