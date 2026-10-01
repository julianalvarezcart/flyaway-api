package pe.edu.utec.flyaway.api.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.utec.flyaway.api.domain.model.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query(value = """
        SELECT b.* FROM bookings b
        JOIN flights f ON f.id = b.flight_id
        WHERE b.customer_id = CAST(:customerId AS uuid)
          AND f.departure_time < CAST(:arrival AS timestamp)
          AND f.arrival_time > CAST(:departure AS timestamp)
        """, nativeQuery = true)
    List<Booking> findOverlappingBookings(
            @Param("customerId") UUID customerId,
            @Param("departure") LocalDateTime departure,
            @Param("arrival") LocalDateTime arrival
    );
}