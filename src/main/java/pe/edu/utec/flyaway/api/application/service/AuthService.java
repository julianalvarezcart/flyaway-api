package pe.edu.utec.flyaway.api.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.utec.flyaway.api.domain.model.User;
import pe.edu.utec.flyaway.api.dto.request.LoginRequest;
import pe.edu.utec.flyaway.api.dto.response.LoginResponse;
import pe.edu.utec.flyaway.api.infrastructure.config.JwtService;
import pe.edu.utec.flyaway.api.infrastructure.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getId(), user.getEmail());

        return LoginResponse.builder()
                .token(token)
                .build();
    }
}