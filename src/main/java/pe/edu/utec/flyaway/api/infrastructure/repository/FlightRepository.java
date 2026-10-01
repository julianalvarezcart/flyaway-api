package pe.edu.utec.flyaway.api.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.utec.flyaway.api.domain.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {
    boolean existsByFlightNumber(String flightNumber);

    @Query(value = """
        SELECT * FROM flights f
        WHERE (CAST(:flightNumber AS text) IS NULL OR LOWER(f.flight_number) LIKE LOWER(CONCAT('%', CAST(:flightNumber AS text), '%')))
          AND (CAST(:airline AS text) IS NULL OR LOWER(f.airline) LIKE LOWER(CONCAT('%', CAST(:airline AS text), '%')))
          AND (CAST(:departureFrom AS timestamp) IS NULL OR f.departure_time >= CAST(:departureFrom AS timestamp))
          AND (CAST(:departureTo AS timestamp) IS NULL OR f.departure_time <= CAST(:departureTo AS timestamp))
        """, nativeQuery = true)
    List<Flight> searchFlights(
            @Param("flightNumber") String flightNumber,
            @Param("airline") String airline,
            @Param("departureFrom") LocalDateTime departureFrom,
            @Param("departureTo") LocalDateTime departureTo
    );
}