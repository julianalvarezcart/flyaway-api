package pe.edu.utec.flyaway.api.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private UUID id;
    private UUID customerId;
    private UUID flightId;
    private String customerFirstName;
    private String customerLastName;
    private String flightNumber;
    private LocalDateTime bookingDate;
}