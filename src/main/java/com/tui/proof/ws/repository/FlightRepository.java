package com.tui.proof.ws.repository;

import com.tui.proof.ws.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

/**
 * Author: Janty
 */

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query(value = "select * from flight where origin=:origin and destination=:destination and date_from<=:dateFrom and date_to>=:dateTo", nativeQuery = true)
    List<Flight> getAvailableFlights(@RequestParam("origin") String origin,
                                     @RequestParam("destination") String destination,
                                     @RequestParam("dateFrom") Date dateFrom,
                                     @RequestParam("dateTo") Date dateTo/*,
                                     @RequestParam("adults") Integer adults,
                                     @RequestParam("children") Integer children,
                                     @RequestParam("infants") Integer infants*/
    );
}
