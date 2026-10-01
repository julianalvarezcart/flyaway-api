package pe.edu.utec.flyaway.api.application.service;

import org.springframework.stereotype.Service;
import pe.edu.utec.flyaway.api.domain.model.Booking;
import pe.edu.utec.flyaway.api.domain.model.Flight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public void sendBookingConfirmation(Booking booking, Flight flight) {
        String fileName = "flight_booking_email_" + booking.getId() + ".txt";

        String content = String.format(
                "Passenger: %s %s%n" +
                        "Flight: %s%n" +
                        "Departure: %s%n" +
                        "Arrival: %s%n" +
                        "Booking Date: %s%n",
                booking.getCustomerFirstName(),
                booking.getCustomerLastName(),
                flight.getFlightNumber(),
                flight.getDepartureTime().format(ISO),
                flight.getArrivalTime().format(ISO),
                booking.getBookingDate().format(ISO)
        );

        try {
            Path path = Paths.get(fileName);
            Files.writeString(path, content);
            System.out.println(">>> EMAIL: Archivo generado -> " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println(">>> EMAIL: Error al escribir el archivo: " + e.getMessage());
        }
    }
}