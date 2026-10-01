package pe.edu.utec.flyaway.api.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utec.flyaway.api.domain.model.Booking;
import pe.edu.utec.flyaway.api.domain.model.Flight;
import pe.edu.utec.flyaway.api.domain.model.User;
import pe.edu.utec.flyaway.api.dto.request.BookingRequest;
import pe.edu.utec.flyaway.api.dto.response.BookingResponse;
import pe.edu.utec.flyaway.api.infrastructure.repository.BookingRepository;
import pe.edu.utec.flyaway.api.infrastructure.repository.FlightRepository;
import pe.edu.utec.flyaway.api.infrastructure.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Transactional
    public BookingResponse bookFlight(UUID userId, BookingRequest request) {
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        LocalDateTime now = LocalDateTime.now();

        if (flight.getDepartureTime().isBefore(now)) {
            throw new IllegalArgumentException("Cannot book past flights");
        }

        if (flight.getAvailableSeats() <= 0) {
            throw new IllegalArgumentException("No available seats");
        }

        List<Booking> conflicts = bookingRepository.findOverlappingBookings(
                userId, flight.getDepartureTime(), flight.getArrivalTime());
        if (!conflicts.isEmpty()) {
            throw new IllegalArgumentException("Schedule conflict");
        }

        Booking booking = Booking.builder()
                .customerId(userId)
                .flightId(flight.getId())
                .customerFirstName(user.getFirstName())
                .customerLastName(user.getLastName())
                .flightNumber(flight.getFlightNumber())
                .bookingDate(now)
                .build();

        Booking saved = bookingRepository.save(booking);

        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightRepository.save(flight);

        emailService.sendBookingConfirmation(saved, flight);
        return toResponse(saved);
    }

    public BookingResponse getBooking(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        return toResponse(booking);
    }

    private BookingResponse toResponse(Booking b) {
        return BookingResponse.builder()
                .id(b.getId())
                .customerId(b.getCustomerId())
                .flightId(b.getFlightId())
                .customerFirstName(b.getCustomerFirstName())
                .customerLastName(b.getCustomerLastName())
                .flightNumber(b.getFlightNumber())
                .bookingDate(b.getBookingDate())
                .build();
    }
}