package pe.edu.utec.flyaway.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "First name is required")
    @Pattern(regexp = ".*[A-Z].*", message = "First name must contain at least one uppercase letter")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = ".*[A-Z].*", message = "Last name must contain at least one uppercase letter")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "Password must contain at least one letter and one number")
    private String password;
}