package acalidonio.bodegamovilbackend.service;

import acalidonio.bodegamovilbackend.dto.AuthResponse;
import acalidonio.bodegamovilbackend.dto.LoginRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
}
