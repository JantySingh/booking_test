package com.tui.proof.ws.repository;

import com.tui.proof.ws.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Janty
 */

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
