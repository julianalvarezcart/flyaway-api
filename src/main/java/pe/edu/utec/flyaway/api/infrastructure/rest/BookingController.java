package pe.edu.utec.flyaway.api.infrastructure.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.edu.utec.flyaway.api.application.service.BookingService;
import pe.edu.utec.flyaway.api.dto.request.BookingRequest;
import pe.edu.utec.flyaway.api.dto.response.BookingResponse;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/flights/book")
    public ResponseEntity<?> bookFlight(@Valid @RequestBody BookingRequest request,
                                        Authentication authentication) {
        try {
            UUID userId = UUID.fromString((String) authentication.getPrincipal());
            BookingResponse response = bookingService.bookFlight(userId, request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/flight/book/{id}")
    public ResponseEntity<?> getBooking(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bookingService.getBooking(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}