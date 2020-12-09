package com.tui.proof.ws.repository;

import com.tui.proof.ws.model.Availability;
import com.tui.proof.ws.model.BookingFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Author: Janty
 */

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
    //List<Availability>  saveAll(List<Availability> availabilityList);
}
