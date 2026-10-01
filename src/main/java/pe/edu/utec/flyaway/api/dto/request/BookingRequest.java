package pe.edu.utec.flyaway.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    @NotNull(message = "Flight ID is required")
    private UUID flightId;
}