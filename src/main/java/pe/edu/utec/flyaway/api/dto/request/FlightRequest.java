package pe.edu.utec.flyaway.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightRequest {

    @NotBlank(message = "Flight number is required")
    @Pattern(regexp = "^[A-Z0-9]{1,6}$", message = "Flight number must be A-Z and 0-9, max 6 chars")
    private String flightNumber;

    @NotBlank(message = "Airline is required")
    private String airline;

    @NotNull(message = "Departure time is required")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time is required")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Available seats is required")
    @Min(value = 1, message = "Available seats must be greater than 0")
    private Integer availableSeats;
}