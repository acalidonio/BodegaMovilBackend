package acalidonio.bodegamovilbackend.auth;

import acalidonio.bodegamovilbackend.common.security.JwtUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findById(request.getEmployeeId());

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (BCrypt.checkpw(request.getPassword(), user.getPasswordHash())) {
                String token = jwtUtil.generateToken(user.getEmployeeId(), user.getRole());
                return new AuthResponse(token, user.getName());
            }
        }
        
        throw new RuntimeException("Credenciales inválidas");
    }
}
