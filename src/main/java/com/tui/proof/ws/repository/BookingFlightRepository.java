package com.tui.proof.ws.repository;

import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.BookingFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author: Janty
 */

@Repository
public interface BookingFlightRepository extends JpaRepository<BookingFlight, Integer> {
    @Query(value = "delete from booking_flight where booking_id=:bookingId and flight_number=:flightId", nativeQuery = true)
    void deleteByFlightNumber(@RequestParam("bookingId") Integer bookingId, @RequestParam("flightId") Integer flightId);
}
