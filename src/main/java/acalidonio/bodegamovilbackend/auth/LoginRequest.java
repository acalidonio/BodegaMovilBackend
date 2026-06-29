package acalidonio.bodegamovilbackend.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String employeeId;
    private String password;
}
