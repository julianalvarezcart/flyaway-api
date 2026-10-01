package pe.edu.utec.flyaway.api.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.utec.flyaway.api.domain.model.Flight;
import pe.edu.utec.flyaway.api.dto.request.FlightRequest;
import pe.edu.utec.flyaway.api.dto.response.FlightResponse;
import pe.edu.utec.flyaway.api.infrastructure.repository.FlightRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightResponse createFlight(FlightRequest request) {
        if (flightRepository.existsByFlightNumber(request.getFlightNumber())) {
            throw new IllegalArgumentException("Flight number already exists");
        }
        if (!request.getDepartureTime().isBefore(request.getArrivalTime())) {
            throw new IllegalArgumentException("Departure must be before arrival");
        }
        if (request.getAvailableSeats() == null || request.getAvailableSeats() <= 0) {
            throw new IllegalArgumentException("Available seats must be > 0");
        }

        Flight flight = Flight.builder()
                .flightNumber(request.getFlightNumber())
                .airline(request.getAirline())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .availableSeats(request.getAvailableSeats())
                .build();

        return toResponse(flightRepository.save(flight));
    }

    public List<FlightResponse> searchFlights(String flightNumber, String airline,
                                              LocalDateTime from, LocalDateTime to) {
        return flightRepository.searchFlights(flightNumber, airline, from, to)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private FlightResponse toResponse(Flight f) {
        return FlightResponse.builder()
                .id(f.getId())
                .flightNumber(f.getFlightNumber())
                .airline(f.getAirline())
                .departureTime(f.getDepartureTime())
                .arrivalTime(f.getArrivalTime())
                .availableSeats(f.getAvailableSeats())
                .build();
    }
}