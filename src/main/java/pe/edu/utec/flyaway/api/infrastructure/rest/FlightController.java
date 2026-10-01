package pe.edu.utec.flyaway.api.infrastructure.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.utec.flyaway.api.application.service.FlightService;
import pe.edu.utec.flyaway.api.dto.request.FlightRequest;
import pe.edu.utec.flyaway.api.dto.response.FlightResponse;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping("/create")
    public ResponseEntity<?> createFlight(@Valid @RequestBody FlightRequest request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(flightService.createFlight(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightResponse>> searchFlights(
            @RequestParam(required = false) String flightNumber,
            @RequestParam(required = false) String airline,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureTo) {
        return ResponseEntity.ok(flightService.searchFlights(flightNumber, airline, departureFrom, departureTo));
    }
}